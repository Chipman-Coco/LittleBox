package com.chipman.littlebox.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentHomeBinding
import com.chipman.littlebox.ui.search.SearchActivity
import com.chipman.model.wanandroid.HomeTabBean
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        const val KEY_CHILD_HOME_TAB_PARCELABLE = "key_child_home_tab_parcelable"
    }

    private lateinit var childAdapter: HomeChildFragmentAdapter

    override val mViewModel: HomeViewModel by viewModels()

    override fun FragmentHomeBinding.initView(savedInstanceState: Bundle?) {
        childAdapter = HomeChildFragmentAdapter(generateHomeTabs(), childFragmentManager, lifecycle)
        with(homeViewPager2) {
            adapter = childAdapter
        }
        with(searchButton) {
            setOnClickListener {
                startActivity(Intent(this@HomeFragment.context, SearchActivity::class.java))
            }
        }
        TabLayoutMediator(
            homeTabLayout,
            homeViewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = childAdapter.items[position].title
        }.apply(TabLayoutMediator::attach)

        with(swipeRefreshLayout) {
            setOnRefreshListener {
                this@HomeFragment.mViewModel.refreshEvent(childAdapter.items[homeViewPager2.currentItem])
                this.isRefreshing = false
            }
        }
    }

    private fun generateHomeTabs() = listOf(
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_EXPLORE),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_SQUARE),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_ANSWER)
    )
}