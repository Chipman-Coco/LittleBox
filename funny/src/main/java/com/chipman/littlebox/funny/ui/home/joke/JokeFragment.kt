package com.chipman.littlebox.funny.ui.home.joke

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chipman.littlebox.funny.R
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.databinding.FragmentJokeBinding
import com.chipman.littlebox.funny.ui.home.HomeFragment
import com.chipman.littlebox.funny.ui.home.HomeTabBean
import com.chipman.littlebox.funny.ui.home.HomeViewModel
import com.chipman.littlebox.funny.util.ktx.launchRepeatOnStarted
import com.chipman.littlebox.funny.widget.RecyclerLinearDivider
import com.chipman.multitype.PagingMultiTypeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class JokeFragment : BaseFragment<FragmentJokeBinding, HomeViewModel>() {

    override val mViewModel by viewModels<HomeViewModel>()

    private val jokeAdapter = PagingMultiTypeAdapter(JokesDiffCalculator.getJokesItemDiffCallback()).apply {
        register(HomeJokeItemBinder(this@JokeFragment::onClick))
    }

    override fun FragmentJokeBinding.initView(savedInstanceState: Bundle?) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                RecyclerLinearDivider.Builder(context)
                    .setDivider(R.drawable.item_shape_divider)
                    .create()
            )
            adapter = jokeAdapter/*.withLoadStateFooter(
                PagingLoadStateAdapter()
            )*/
            setHasFixedSize(true)
        }
        with(swipeRefreshLayout) {

        }
        initData()
    }

    override fun initData() {
        launchRepeatOnStarted {
            launch {
                mViewModel.recommendList.collectLatest {
                    jokeAdapter.submitData(it)
                }
            }
        }
    }

    private fun onClick(action: JokeAction) {
        when (action) {
            is JokeAction.AttentionAction -> {
                Timber.i("attention action user: ${action.user}, position: ${action.position}")
            }
            is JokeAction.LikeAction -> {
                Timber.i("like action position: ${action.position}")
            }
            is JokeAction.UnLikeAction -> {
                Timber.i("unlike action position: ${action.position}")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(tabBean: HomeTabBean) =
            JokeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, tabBean)
                }
            }
    }
}