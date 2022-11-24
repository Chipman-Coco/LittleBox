package com.chipman.littlebox.wanandroid.ui.home.child.square

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentSquareBinding

class SquareFragment : BaseFragment<FragmentSquareBinding, SquareViewModel>() {

    companion object {
        fun newInstance() = SquareFragment().apply {

        }
    }

    override val mViewModel: SquareViewModel by viewModels()

    override fun FragmentSquareBinding.initView(savedInstanceState: Bundle?) {

    }
}