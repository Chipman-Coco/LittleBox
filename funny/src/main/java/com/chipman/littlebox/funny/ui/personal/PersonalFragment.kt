package com.chipman.littlebox.funny.ui.personal

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.FragmentPersonalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalFragment : BaseFragment<FragmentPersonalBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun FragmentPersonalBinding.initView(savedInstanceState: Bundle?) {

    }

}