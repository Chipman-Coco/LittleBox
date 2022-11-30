package com.chipman.littlebox.eyepetizer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chipman.littlebox.eyepetizer.R
import com.chipman.littlebox.eyepetizer.databinding.ActivityMainBinding
import com.chipman.littlebox.eyepetizer.ui.main.community.CommunityFragment
import com.chipman.littlebox.eyepetizer.ui.main.home.HomeFragment
import com.chipman.littlebox.eyepetizer.ui.main.notification.NotificationFragment
import com.chipman.littlebox.eyepetizer.ui.main.personal.PersonalFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var curFragmentIndex: Int = -1

    private var fragmentList = listOf(
        HomeFragment(),
        CommunityFragment(),
        NotificationFragment(),
        PersonalFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
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
            supportFragmentManager.findFragmentByTag(it.javaClass.toString()) as? Fragment ?: it
        }
        switchFragment(savedInstanceState.getInt(KEY_CURRENT_FRAGMENT_INDEX, 0))
    }

    private fun initView() {
        mBinding.bottomNaviView.setOnItemSelectedListener {
            switchFragment(getFragmentIndex(it.itemId))
            return@setOnItemSelectedListener true
        }
    }

    private fun getFragmentIndex(itemId: Int): Int {
        return when (itemId) {
            R.id.home -> 0
            R.id.community -> 1
            R.id.notification -> 2
            R.id.my -> 3
            else -> 0
        }
    }

    private fun switchFragment(index: Int) {
        if (curFragmentIndex != index) {
            val transaction = supportFragmentManager.beginTransaction()
            val newFragment = fragmentList[index]
            fragmentList.getOrNull(curFragmentIndex)?.apply(transaction::hide)
            if (!newFragment.isAdded) {
                transaction.add(
                    R.id.fragment_container,
                    newFragment,
                    newFragment.javaClass.simpleName
                ).show(newFragment)
            } else {
                transaction.show(newFragment)
            }
            transaction.commitAllowingStateLoss()
            curFragmentIndex = index
        }
    }

    companion object {
        private const val KEY_CURRENT_FRAGMENT_INDEX = "key_current_fragment_index"
    }
}