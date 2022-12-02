package com.chipman.littlebox.funny.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chipman.littlebox.funny.util.BindingReflex

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected val mBinding: VB by lazy(LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    protected abstract val mViewModel: VM

    abstract fun VB.initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.initView(savedInstanceState)
    }
}