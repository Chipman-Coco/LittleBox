package com.chipman.serve

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import com.chipman.serve.aidl.IBinderPool
import timber.log.Timber

class BinderPool private constructor() {

    companion object {
        private const val ACTION_POOL_SERVICE = "com.chipman.android.action.BinderPoolService"

        @Volatile
        private var mInstance: BinderPool? = null

        fun getInstance(): BinderPool =
            mInstance ?: synchronized(this) {
                mInstance ?: BinderPool().also { mInstance = it }
            }
    }

    private lateinit var mContext: Context
    private var mBinderPool: IBinderPool? = null
    private var isBind: Boolean = false
//    private var mCountDownLatch: CountDownLatch? = null

    // Service 死亡重连
    private var mDeathRecipient = object : IBinder.DeathRecipient {
        override fun binderDied() {
            Timber.e("binder died...")
            mBinderPool?.asBinder()?.unlinkToDeath(this, 0)
            mBinderPool = null
            isBind = false
            // 连接服务
            connectBinderPoolService()
        }
    }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Timber.d("service connected...")
            service?.let {
                mBinderPool = IBinderPool.Stub.asInterface(it) as IBinderPool
                try {
                    mBinderPool!!.asBinder().linkToDeath(mDeathRecipient, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                isBind = true
//                mCountDownLatch!!.countDown()
            } ?: Timber.e("service is null.")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.e("service disconnected...")
            isBind = false
        }
    }

    fun initBinderPool(context: Context) {
        Timber.d("init binder pool...")
        mContext = context
        connectBinderPoolService()
    }

    fun queryBinder(binderCode: Int): IBinder? {
        var iBinder: IBinder? = null
        mBinderPool?.let {
            try {
                iBinder = it.queryBinder(binderCode)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        return iBinder
    }

    @Synchronized
    private fun connectBinderPoolService() {
//        mCountDownLatch = CountDownLatch(1)
        val intent = Intent().apply {
            action = ACTION_POOL_SERVICE
            setPackage(mContext.packageName)
        }
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
//        try {
//            mCountDownLatch!!.await()
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
    }
}