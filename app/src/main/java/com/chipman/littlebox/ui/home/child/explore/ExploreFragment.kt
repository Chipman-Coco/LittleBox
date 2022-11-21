package com.chipman.littlebox.ui.home.child.explore

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.adapter.UiModelAdapter
import com.chipman.littlebox.databinding.FragmentExploreBinding
import com.chipman.littlebox.ui.home.HomeFragment
import com.chipman.model.wanandroid.HomeTabBean
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding, ExploreViewModel>() {

//    private var iMessageManager: IMessageManager? = null

    private lateinit var homeAdapter: UiModelAdapter

    companion object {
        fun newInstance(homeTabBean: HomeTabBean) = ExploreFragment().apply {
            arguments = bundleOf(Pair(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, homeTabBean))
        }
    }

    override val mViewModel: ExploreViewModel by viewModels()

    override fun FragmentExploreBinding.initView(savedInstanceState: Bundle?) {
        homeAdapter = UiModelAdapter(this@ExploreFragment::onItemClick)
        with(exploreList) {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
            setHasFixedSize(true)
        }
        initData()
    }

    private fun initData() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    mViewModel.articlesFlow.collectLatest {
//                        homeAdapter.submitData(it)
//                    }
//                }
//            }
//        }
    }

    private fun onItemClick(articleClick: ArticleAction) {

    }

//    private fun startServe() {
////        lifecycleScope.launch(Dispatchers.Default) {
////            BinderPool.getInstance().initBinderPool(BaseApp.appContext)
//            BinderPool.getInstance().queryBinder(BinderPool.BINDER_MESSAGE)?.let {
//                Timber.d("message manager init...")
//                iMessageManager = IMessageManager.Stub.asInterface(it)
//                iMessageManager!!.registerReceiveListener(object : IMessageCallbackListener.Stub() {
//                    override fun onReceive(msg: Message?) {
//                        Timber.d(msg?.msg ?: "")
//                    }
//                })
//            }
////        }
//    }

}