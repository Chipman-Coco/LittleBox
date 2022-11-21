package com.chipman.littlebox

import com.chipman.common.BaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope

@HiltAndroidApp
class App : BaseApp() {

    private val mCoroutineScope by lazy(LazyThreadSafetyMode.NONE) { MainScope() }

    override fun onCreate() {
        super.onCreate()

//        mCoroutineScope.launch {
//            BinderPool.getInstance().initBinderPool(appContext)
//        }
    }
}