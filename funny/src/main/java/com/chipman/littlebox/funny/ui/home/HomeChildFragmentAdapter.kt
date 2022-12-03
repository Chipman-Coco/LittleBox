package com.chipman.littlebox.funny.ui.home

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chipman.littlebox.funny.ui.home.attention.AttentionFragment
import com.chipman.littlebox.funny.ui.home.joke.JokeFragment
import kotlinx.parcelize.Parcelize

class HomeChildFragmentAdapter(
    var items: List<HomeTabBean>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val HOME_TAB_1 = "关注"
        const val HOME_TAB_2 = "推荐"
        const val HOME_TAB_3 = "新鲜"
        const val HOME_TAB_4 = "纯文"
        const val HOME_TAB_5 = "趣图"
    }

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position].title) {
            HOME_TAB_1 -> AttentionFragment.newInstance(items[position])
            else -> JokeFragment.newInstance(items[position])
        }
    }
}

/**
 * 首页 Tab 实体类
 */
@Parcelize
data class HomeTabBean(
    val title: String
) : Parcelable