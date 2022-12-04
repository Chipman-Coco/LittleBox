package com.chipman.littlebox.funny.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.chipman.littlebox.funny.R
import com.chipman.littlebox.funny.base.BaseActivity
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.databinding.ActivityMainBinding
import com.chipman.littlebox.funny.ui.home.HomeFragment
import com.chipman.littlebox.funny.ui.message.MessageFragment
import com.chipman.littlebox.funny.ui.personal.PersonalFragment
import com.chipman.littlebox.funny.ui.tiktok.TiktokFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        const val KEY_CURRENT_FRAGMENT_INDEX = "key_current_fragment_index"
    }

    private var curFragmentIndex = -1

    private var fragmentList = listOf(
        HomeFragment(),
        TiktokFragment(),
        MessageFragment(),
        PersonalFragment()
    )

    override val mViewModel: MainViewModel by viewModels()

    override fun ActivityMainBinding.initView(savedInstanceState: Bundle?) {
        bottomNavi.setOnItemSelectedListener {
            val idx = getFragmentIndexById(it)
            switchFragment(idx)
            true
        }
        if (savedInstanceState == null) {
            switchFragment(0)
        }
        changeMessageDot(9)
    }

    override fun initData() {
        // TODO("Not yet implemented")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(KEY_CURRENT_FRAGMENT_INDEX, curFragmentIndex)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        fragmentList = fragmentList.map {
            supportFragmentManager.findFragmentByTag(it.javaClass.simpleName) as? BaseFragment<*, *>
                ?: it
        }
        switchFragment(savedInstanceState.getInt(KEY_CURRENT_FRAGMENT_INDEX))
    }

    private fun onBottomItemDouble(item: MenuItem) {

    }

    private fun switchFragment(index: Int) {
        if (index != curFragmentIndex) {
            val transaction = supportFragmentManager.beginTransaction()
            val newFragment = fragmentList[index]
            fragmentList.getOrNull(curFragmentIndex)?.apply(transaction::hide)
            if (newFragment.isAdded) {
                transaction.show(newFragment)
            } else {
                newFragment.let {
                    transaction.add(R.id.fragment_container, it, it.javaClass.simpleName).show(it)
                }
            }
            transaction.commitAllowingStateLoss()
            curFragmentIndex = index
        }
    }

    private fun getFragmentIndexById(menuItem: MenuItem) =
        when (menuItem.itemId) {
            R.id.bottom_navi_1 -> 0
            R.id.bottom_navi_2 -> 1
//            R.id.bottom_navi_3 -> 2
            R.id.bottom_navi_4 -> 2
            R.id.bottom_navi_5 -> 3
            else -> 0
        }

    private fun changeMessageDot(number: Int) {
        mBinding.bottomNavi.getOrCreateBadge(R.id.bottom_navi_4).also { badge ->
            badge.number = number
            badge.maxCharacterCount = 3
            badge.backgroundColor = getColor(R.color.material_red_A700)
            badge.isVisible = number > 0
        }
    }
}