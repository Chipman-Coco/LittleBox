package com.chipman.littlebox.funny.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.chipman.littlebox.funny.base.BaseActivity
import com.chipman.littlebox.funny.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun ActivityMainBinding.initView(savedInstanceState: Bundle?) {
//        mViewModel.getAttentionList()
        mViewModel.getCode()
    }

}