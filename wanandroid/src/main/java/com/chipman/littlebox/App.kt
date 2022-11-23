package com.chipman.littlebox

import android.content.Context
import com.chipman.common.BaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope

@HiltAndroidApp
class App : BaseApp() {

    private val mCoroutineScope by lazy(LazyThreadSafetyMode.NONE) { MainScope() }

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}