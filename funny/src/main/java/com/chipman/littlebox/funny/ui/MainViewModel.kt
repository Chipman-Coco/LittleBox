package com.chipman.littlebox.funny.ui

import com.chipman.littlebox.funny.BuildConfig
import com.chipman.littlebox.funny.base.BaseViewModel
import com.chipman.littlebox.funny.data.Constant
import com.chipman.littlebox.funny.data.result.getOrNull
import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.HomeService
import com.chipman.littlebox.funny.service.UserService
import com.chipman.littlebox.funny.service.WeatherService
import com.chipman.littlebox.funny.util.ktx.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val homeService by lazy { RetrofitManager.getService(HomeService::class.java) }

    private val userService by lazy { RetrofitManager.getService(UserService::class.java) }

    private val weatherService by lazy {
        RetrofitManager.getService(
            WeatherService::class.java,
            baseUrl = Constant.ROLL_HOST
        )
    }

    fun getAttentionList() {
        launchMain {
            val list = homeService.getAttentionList(0).getOrNull()
            Timber.d("list: $list")
        }
    }

    fun getCode() {
        launchMain {
            val resp = userService.getCode(BuildConfig.PHONE).getOrNull()
            Timber.d("loginPsw: $resp")
        }
    }

    fun getWeather() {
        launchMain {
            val resp = weatherService.getCurrentWeather("深圳市")
            Timber.d("weather: $resp")
        }
    }

}