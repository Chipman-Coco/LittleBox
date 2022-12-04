package com.chipman.littlebox.funny.ui.search

import android.os.Bundle
import androidx.activity.viewModels
import com.chipman.littlebox.funny.base.BaseActivity
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun ActivitySearchBinding.initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        // TODO("Not yet implemented")
    }
}