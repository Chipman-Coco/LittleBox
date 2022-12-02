package com.chipman.common

import android.app.Application
import android.content.Context
import com.chipman.common.util.SpUtil
import com.hjq.toast.ToastUtils
import timber.log.Timber

open class BaseApp : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        initToast()
        initTimber()
        initMMKV()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    /** 初始化 ToastUtil */
    private fun initToast() {
        ToastUtils.init(this)
    }

    /** 初始化日志输出 */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /** 初始化MMKV */
    private fun initMMKV() {
        SpUtil.initMMKV(appContext)
    }
}