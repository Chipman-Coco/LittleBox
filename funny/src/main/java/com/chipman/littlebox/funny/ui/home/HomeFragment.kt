package com.chipman.littlebox.funny.ui.home

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.R
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
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
        with(tabLayout) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView = TextView(context).apply {
                        textSize = 20f
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                        text = tab?.text
                        setTextColor(context.getColor(R.color.material_orange_700))
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView = null
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
        btSearch.setOnClickListener {

        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = childAdapter.items[position].title
        }.attach()
    }

    override fun initData() {
        // TODO("Not yet implemented")
    }

    private fun generateHomeTabs() = listOf(
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_1),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_2),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_3),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_4),
        HomeTabBean(HomeChildFragmentAdapter.HOME_TAB_5)
    )
}