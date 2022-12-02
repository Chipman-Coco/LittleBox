package com.chipman.littlebox.funny.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chipman.littlebox.funny.util.BindingReflex

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _mBinding: VB? = null

    protected val mBinding get() = _mBinding!!

    protected abstract val mViewModel: VM

    abstract fun VB.initView(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _mBinding = BindingReflex.reflexViewBinding(javaClass, layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initView(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}