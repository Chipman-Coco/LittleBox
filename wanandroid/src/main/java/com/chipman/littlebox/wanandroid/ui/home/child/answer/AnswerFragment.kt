package com.chipman.littlebox.wanandroid.ui.home.child.answer

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentAnswerBinding
import com.chipman.littlebox.wanandroid.ui.home.HomeFragment
import com.chipman.model.wanandroid.HomeTabBean

class AnswerFragment : BaseFragment<FragmentAnswerBinding, AnswerViewModel>() {

    companion object {
        fun newInstance(homeTabBean: HomeTabBean) = AnswerFragment().apply {
            arguments = bundleOf(Pair(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, homeTabBean))
        }
    }

    override val mViewModel: AnswerViewModel by viewModels()

    override fun FragmentAnswerBinding.initView(savedInstanceState: Bundle?) {

    }
}