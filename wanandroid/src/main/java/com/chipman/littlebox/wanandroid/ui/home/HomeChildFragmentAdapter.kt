package com.chipman.littlebox.wanandroid.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chipman.littlebox.wanandroid.ui.home.child.answer.AnswerFragment
import com.chipman.littlebox.wanandroid.ui.home.child.explore.ExploreFragment
import com.chipman.littlebox.wanandroid.ui.home.child.square.SquareFragment
import com.chipman.model.wanandroid.HomeTabBean

class HomeChildFragmentAdapter(
    var items: List<HomeTabBean>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val HOME_TAB_EXPLORE = "首页"
        const val HOME_TAB_SQUARE = "广场"
        const val HOME_TAB_ANSWER = "问答"
    }

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position].title) {
            HOME_TAB_EXPLORE -> ExploreFragment.newInstance(items[position])
            HOME_TAB_ANSWER -> AnswerFragment.newInstance()
            HOME_TAB_SQUARE -> SquareFragment.newInstance()
            else -> ExploreFragment.newInstance(items[position])
        }
    }
}