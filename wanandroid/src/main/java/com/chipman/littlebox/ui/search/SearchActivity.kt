package com.chipman.littlebox.ui.search

import android.os.Bundle
import androidx.activity.viewModels
import com.chipman.littlebox.BaseActivity
import com.chipman.littlebox.wanandroid.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val mViewModel: SearchViewModel by viewModels()

    override fun ActivitySearchBinding.initView(savedInstanceState: Bundle?) {

    }

}