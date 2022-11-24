package com.chipman.littlebox.wanandroid.ui.group

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentGroupBinding

class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>() {

    override val mViewModel: GroupViewModel by viewModels()

    override fun FragmentGroupBinding.initView(savedInstanceState: Bundle?) {

    }
}