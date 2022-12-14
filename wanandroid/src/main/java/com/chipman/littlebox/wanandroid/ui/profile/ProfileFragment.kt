package com.chipman.littlebox.wanandroid.ui.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val mViewModel: ProfileViewModel by viewModels()

    override fun FragmentProfileBinding.initView(savedInstanceState: Bundle?) {

    }
}