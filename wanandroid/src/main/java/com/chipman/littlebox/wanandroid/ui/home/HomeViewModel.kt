package com.chipman.littlebox.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chipman.common.base.BaseViewModel
import com.chipman.model.wanandroid.HomeTabBean

class HomeViewModel : BaseViewModel() {

    private val _refreshLiveData = MutableLiveData<HomeTabBean>()
    val refreshLiveData: LiveData<HomeTabBean> = _refreshLiveData

    fun refreshEvent(tabBean: HomeTabBean) {
        _refreshLiveData.value = tabBean
    }
}