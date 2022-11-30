package com.chipman.littlebox.wanandroid.ui.home.child.explore

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentExploreBinding
import com.chipman.littlebox.wanandroid.ktx.isRefreshing
import com.chipman.littlebox.wanandroid.ui.ArticleDiffCalculator
import com.chipman.littlebox.wanandroid.ui.SimpleFooterItemBinder
import com.chipman.littlebox.wanandroid.ui.home.HomeFragment
import com.chipman.littlebox.wanandroid.ui.home.item.HomeArticleItemBinder
import com.chipman.littlebox.wanandroid.ui.home.item.HomeBannerItemBinder
import com.chipman.model.wanandroid.Banner
import com.chipman.model.wanandroid.HomeTabBean
import com.chipman.multitype.PagingLoadStateAdapter
import com.chipman.multitype.PagingMultiTypeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding, ExploreViewModel>() {

    companion object {
        fun newInstance(homeTabBean: HomeTabBean) = ExploreFragment().apply {
            arguments = bundleOf(Pair(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, homeTabBean))
        }
    }

    private val exploreAdapter =
        PagingMultiTypeAdapter(ArticleDiffCalculator.getCommonDiffItemCallback()).apply {
            register(HomeBannerItemBinder(this@ExploreFragment::onBannerItemClick))
            register(HomeArticleItemBinder(this@ExploreFragment::onItemClick))
        }

    override val mViewModel: ExploreViewModel by viewModels()

    override fun FragmentExploreBinding.initView(savedInstanceState: Bundle?) {
        with(exploreList) {
            layoutManager = LinearLayoutManager(context)
            adapter = exploreAdapter.withLoadStateFooter(
                PagingLoadStateAdapter(
                    SimpleFooterItemBinder(),
                    exploreAdapter.types
                )
            )
            setHasFixedSize(true)
        }
        initData()
    }

    private fun initData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mViewModel.articlesFlow.collectLatest {
                        exploreAdapter.submitData(it)
                    }
                }
                launch {
                    exploreAdapter.loadStateFlow.collectLatest {
                        updateLoadStatus(it)
                    }
                }
            }
        }
    }

    private fun onItemClick(articleClick: ArticleAction) {

    }

    private fun onBannerItemClick(data: Banner, position: Int) {
//        WebActivity.loadUrl(this.requireContext(), data.url)
    }

    private fun updateLoadStatus(loadStates: CombinedLoadStates) {
        with(mBinding.loadingContainer) {
            emptyLayout.isVisible = loadStates.refresh is LoadState.NotLoading && exploreAdapter.itemCount == 0
            loadingProgress.isVisible = exploreAdapter.itemCount == 0 && loadStates.isRefreshing
        }
    }
}