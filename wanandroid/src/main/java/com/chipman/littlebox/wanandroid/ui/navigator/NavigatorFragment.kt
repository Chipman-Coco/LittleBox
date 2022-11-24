package com.chipman.littlebox.wanandroid.ui.navigator

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.wanandroid.BaseFragment
import com.chipman.littlebox.wanandroid.databinding.FragmentNavigatorBinding

class NavigatorFragment : BaseFragment<FragmentNavigatorBinding, NavigatorViewModel>() {

    override val mViewModel: NavigatorViewModel by viewModels()

    override fun FragmentNavigatorBinding.initView(savedInstanceState: Bundle?) {

    }
}