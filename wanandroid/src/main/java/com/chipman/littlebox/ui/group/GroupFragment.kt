package com.chipman.littlebox.ui.group

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentGroupBinding

class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>() {

    override val mViewModel: GroupViewModel by viewModels()

    override fun FragmentGroupBinding.initView(savedInstanceState: Bundle?) {

    }
}