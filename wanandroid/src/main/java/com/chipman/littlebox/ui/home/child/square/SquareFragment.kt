package com.chipman.littlebox.ui.home.child.square

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentSquareBinding

class SquareFragment : BaseFragment<FragmentSquareBinding, SquareViewModel>() {

    companion object {
        fun newInstance() = SquareFragment().apply {

        }
    }

    override val mViewModel: SquareViewModel by viewModels()

    override fun FragmentSquareBinding.initView(savedInstanceState: Bundle?) {

    }
}