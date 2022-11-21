package com.chipman.serve

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.chipman.serve.impl.BinderPoolImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BinderPoolService : Service() {

    @Inject
    lateinit var mBinderPoolImpl: BinderPoolImpl

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinderPoolImpl
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}