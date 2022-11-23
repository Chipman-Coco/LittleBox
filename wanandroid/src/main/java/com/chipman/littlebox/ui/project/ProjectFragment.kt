package com.chipman.littlebox.ui.project

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.BaseFragment
import com.chipman.littlebox.databinding.FragmentProjectBinding

class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {

    override val mViewModel: ProjectViewModel by viewModels()

    override fun FragmentProjectBinding.initView(savedInstanceState: Bundle?) {

    }
}