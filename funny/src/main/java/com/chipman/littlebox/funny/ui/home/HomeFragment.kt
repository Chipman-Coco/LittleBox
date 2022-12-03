package com.chipman.littlebox.funny.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        const val KEY_CHILD_HOME_TAB_PARCELABLE = "key_child_tab_parcelable"
    }

    private lateinit var childAdapter: HomeChildFragmentAdapter

    override val mViewModel: HomeViewModel by viewModels()

    override fun FragmentHomeBinding.initView(savedInstanceState: Bundle?) {
        childAdapter = HomeChildFragmentAdapter(
            generateHomeTabs(),
            this@HomeFragment.childFragmentManager,
            lifecycle
        )
        with(viewPager) {
            adapter = childAdapter
        }
        btSearch.setOnClickListener {

        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = childAdapter.items[position].title
        }.attach()
    }

    private fun generateHomeTabs() = listOf(
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_1),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_2),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_3),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_4),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_5)
    )
}