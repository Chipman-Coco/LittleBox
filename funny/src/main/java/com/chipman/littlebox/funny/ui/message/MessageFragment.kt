package com.chipman.littlebox.funny.ui.message

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.FragmentMessageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun FragmentMessageBinding.initView(savedInstanceState: Bundle?) {

    }

}