package com.chipman.littlebox.ui.home.child.answer

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentAnswerBinding

class AnswerFragment : BaseFragment<FragmentAnswerBinding, AnswerViewModel>() {

    companion object {
        fun newInstance() = AnswerFragment().apply {

        }
    }

    override val mViewModel: AnswerViewModel by viewModels()

    override fun FragmentAnswerBinding.initView(savedInstanceState: Bundle?) {

    }
}