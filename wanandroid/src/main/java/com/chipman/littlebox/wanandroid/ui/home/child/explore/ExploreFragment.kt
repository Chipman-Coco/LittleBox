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
import com.chipman.littlebox.wanandroid.adapter.UiModelAdapter
import com.chipman.littlebox.wanandroid.databinding.FragmentExploreBinding
import com.chipman.littlebox.wanandroid.ktx.isRefreshing
import com.chipman.littlebox.wanandroid.ui.home.HomeFragment
import com.chipman.littlebox.wanandroid.widget.PagingLoadFooterAdapter
import com.chipman.model.wanandroid.HomeTabBean
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding, ExploreViewModel>() {

    private lateinit var mAdapter: UiModelAdapter

    companion object {
        fun newInstance(homeTabBean: HomeTabBean) = ExploreFragment().apply {
            arguments = bundleOf(Pair(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, homeTabBean))
        }
    }

    override val mViewModel: ExploreViewModel by viewModels()

    override fun FragmentExploreBinding.initView(savedInstanceState: Bundle?) {
        mAdapter = UiModelAdapter(this@ExploreFragment::onItemClick).apply {
            addLoadStateListener(this@ExploreFragment::updateLoadStatus)
        }
        with(exploreList) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter.withLoadStateFooter(
                PagingLoadFooterAdapter {
                    mAdapter.retry()
                }
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
                        Timber.d("articlesFlow: $it")
                        mAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun onItemClick(articleClick: ArticleAction) {

    }

    private fun updateLoadStatus(loadStates: CombinedLoadStates) {
        with(mBinding.loadingContainer) {
            emptyLayout.isVisible = loadStates.refresh is LoadState.NotLoading && mAdapter.itemCount == 0
            loadingProgress.isVisible = mAdapter.itemCount == 0 && loadStates.isRefreshing
        }
    }
}