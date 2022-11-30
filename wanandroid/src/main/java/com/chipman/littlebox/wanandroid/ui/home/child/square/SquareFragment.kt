package com.chipman.littlebox.wanandroid.ui.home.child.square

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
import com.chipman.littlebox.wanandroid.databinding.FragmentSquareBinding
import com.chipman.littlebox.wanandroid.ktx.isRefreshing
import com.chipman.littlebox.wanandroid.ui.ArticleDiffCalculator
import com.chipman.littlebox.wanandroid.ui.SimpleFooterItemBinder
import com.chipman.littlebox.wanandroid.ui.home.HomeFragment
import com.chipman.littlebox.wanandroid.ui.home.item.HomeArticleItemBinder
import com.chipman.model.wanandroid.HomeTabBean
import com.chipman.multitype.PagingLoadStateAdapter
import com.chipman.multitype.PagingMultiTypeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SquareFragment : BaseFragment<FragmentSquareBinding, SquareViewModel>() {

    companion object {
        fun newInstance(homeTabBean: HomeTabBean) = SquareFragment().apply {
            arguments = bundleOf(Pair(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, homeTabBean))
        }
    }

    private val squareAdapter =
        PagingMultiTypeAdapter(ArticleDiffCalculator.getCommonDiffItemCallback()).apply {
            register(HomeArticleItemBinder(this@SquareFragment::onItemClick))
        }

    override val mViewModel: SquareViewModel by viewModels()

    override fun FragmentSquareBinding.initView(savedInstanceState: Bundle?) {
        with(squareList) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = squareAdapter.withLoadStateFooter(
                PagingLoadStateAdapter(
                    SimpleFooterItemBinder(),
                    squareAdapter.types
                )
            )
        }
        initData()
    }

    private fun initData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mViewModel.collectSquareFlow.collectLatest {
                        squareAdapter.submitData(it)
                    }
                }
                launch {
                    squareAdapter.loadStateFlow.collectLatest {
                        updateLoadStatus(it)
                    }
                }
            }
        }
    }

    private fun onItemClick(articleAction: ArticleAction) {
        when (articleAction) {
            is ArticleAction.ItemClick -> {}/*WebActivity.loadUrl(
                requireContext(),
                Activities.Web.WebIntent(
                    articleAction.article.link,
                    articleAction.article.id,
                    articleAction.article.collect,
                )
            )*/
            is ArticleAction.CollectClick -> {
//                appViewModel.articleCollectAction(
//                    CollectEvent(
//                        articleAction.article.id,
//                        articleAction.article.link,
//                        articleAction.article.collect.not()
//                    )
//                )
            }
            else -> {}
        }
    }

    private fun updateLoadStatus(loadStates: CombinedLoadStates) {
        with(mBinding.loadingContainer) {
            emptyLayout.isVisible = loadStates.refresh is LoadState.NotLoading && squareAdapter.itemCount == 0
            loadingProgress.isVisible = squareAdapter.itemCount == 0 && loadStates.isRefreshing
        }
    }
}