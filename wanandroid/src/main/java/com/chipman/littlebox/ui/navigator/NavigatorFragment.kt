package com.chipman.littlebox.ui.navigator

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentNavigatorBinding

class NavigatorFragment : BaseFragment<FragmentNavigatorBinding, NavigatorViewModel>() {

    override val mViewModel: NavigatorViewModel by viewModels()

    override fun FragmentNavigatorBinding.initView(savedInstanceState: Bundle?) {

    }
}