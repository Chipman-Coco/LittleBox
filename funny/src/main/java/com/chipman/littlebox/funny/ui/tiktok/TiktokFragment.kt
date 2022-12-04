package com.chipman.littlebox.funny.ui.tiktok

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.FragmentTiktokBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiktokFragment : BaseFragment<FragmentTiktokBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun FragmentTiktokBinding.initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        // TODO("Not yet implemented")
    }
}