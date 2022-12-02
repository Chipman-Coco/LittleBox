package com.chipman.littlebox.funny.ui

import com.chipman.littlebox.funny.BuildConfig
import com.chipman.littlebox.funny.api.HomeService
import com.chipman.littlebox.funny.api.UserService
import com.chipman.littlebox.funny.api.result.getOrNull
import com.chipman.littlebox.funny.base.BaseViewModel
import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.util.ktx.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val homeService by lazy { RetrofitManager.getService(HomeService::class.java) }

    private val userService by lazy { RetrofitManager.getService(UserService::class.java) }

    fun getAttentionList() {
        launch {
            val list = homeService.getAttentionList(0).getOrNull()
            Timber.d("list: $list")
        }
    }

    fun getCode() {
        launch {
            val resp = userService.getCode(BuildConfig.PHONE).getOrNull()
            Timber.d("loginPsw: $resp")
        }
    }

}