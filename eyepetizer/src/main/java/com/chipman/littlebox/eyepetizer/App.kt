package com.chipman.littlebox.eyepetizer

import android.content.Context
import com.chipman.common.BaseApp

class App : BaseApp() {

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}