package com.chipman.littlebox.funny

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hjq.toast.ToastUtils
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        initTools()
    }

    private fun initTools() {
        initMMKV()
        ToastUtils.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initMMKV(): String {
        val rootDir = filesDir.absolutePath + "/mmkv"
        return MMKV.initialize(this, rootDir)
    }
}