package com.chipman.littlebox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.chipman.littlebox.ui.group.GroupFragment
import com.chipman.littlebox.ui.home.HomeFragment
import com.chipman.littlebox.ui.navigator.NavigatorFragment
import com.chipman.littlebox.ui.profile.ProfileFragment
import com.chipman.littlebox.ui.project.ProjectFragment
import com.chipman.littlebox.wanandroid.R
import com.chipman.littlebox.wanandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        private const val KEY_CURRENT_FRAGMENT_INDEX = "key_current_fragment_index"

        fun start(fromActivity: Activity) {
            val intent = Intent(fromActivity, MainActivity::class.java)
            fromActivity.startActivity(intent)
        }
    }

    private var curFragmentIndex = -1

    private var fragmentList = listOf<Fragment>(
        HomeFragment(),
        ProjectFragment(),
        NavigatorFragment(),
        GroupFragment(),
        ProfileFragment()
    )

    override val mViewModel: MainViewModel by viewModels()

    override fun ActivityMainBinding.initView(savedInstanceState: Bundle?) {
        bottomNavView.setOnItemSelectedListener {
            switchFragment(getFragmentIndexFromItemId(it.itemId))
            return@setOnItemSelectedListener true
        }
        if (savedInstanceState == null) {
            switchFragment(0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CURRENT_FRAGMENT_INDEX, curFragmentIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        fragmentList = fragmentList.map {
            supportFragmentManager.findFragmentByTag(it.javaClass.simpleName) as? BaseFragment<*, *> ?: it
        }
        switchFragment(savedInstanceState.getInt(KEY_CURRENT_FRAGMENT_INDEX, 0))
    }

    private fun switchFragment(fragmentIndex: Int) {
        if (fragmentIndex != curFragmentIndex) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = fragmentList[fragmentIndex]
            fragmentList.getOrNull(curFragmentIndex)?.apply(fragmentTransaction::hide)
            if (!fragment.isAdded) {
                fragmentTransaction
                    .add(
                        R.id.fragment_container_view,
                        fragment,
                        fragment.javaClass.simpleName
                    )
                    .show(fragment)
            } else {
                fragmentTransaction.show(fragment)
            }
            fragmentTransaction.commitAllowingStateLoss()
            curFragmentIndex = fragmentIndex
        }
    }

    private fun getFragmentIndexFromItemId(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_home -> 0
            R.id.navigation_project -> 1
            R.id.navigation_navigator -> 2
            R.id.navigation_wx_group -> 3
            R.id.navigation_profile -> 4
            else -> 0
        }
    }
}