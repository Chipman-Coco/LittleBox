package com.chipman.littlebox.eyepetizer.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chipman.littlebox.eyepetizer.App
import com.chipman.littlebox.eyepetizer.R
import com.chipman.littlebox.eyepetizer.databinding.FragmentHomeBinding
import com.chipman.littlebox.eyepetizer.ui.main.home.daily.DailyFragment
import com.chipman.littlebox.eyepetizer.ui.main.home.discover.DiscoverFragment
import com.chipman.littlebox.eyepetizer.ui.main.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 首页
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val tabTitle = arrayListOf(
        App.mContext.resources.getString(R.string.discover),
        App.mContext.resources.getString(R.string.recommend),
        App.mContext.resources.getString(R.string.daily)
    )

    private val fragmentList = arrayListOf(
        DiscoverFragment(),
        RecommendFragment(),
        DailyFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            viewPager.adapter = object : FragmentStateAdapter(this@HomeFragment) {
                override fun getItemCount(): Int = tabTitle.size

                override fun createFragment(position: Int): Fragment = fragmentList[position]
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}