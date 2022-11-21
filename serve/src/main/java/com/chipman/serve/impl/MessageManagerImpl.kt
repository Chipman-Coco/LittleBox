package com.chipman.serve.impl

import android.os.RemoteCallbackList
import com.chipman.serve.aidl.IMessageCallbackListener
import com.chipman.serve.aidl.IMessageManager
import com.chipman.serve.aidl.Message

internal class MessageManagerImpl private constructor(): IMessageManager.Stub() {

    // Client 回调监听集合
    private val mCallbackListener = RemoteCallbackList<IMessageCallbackListener>()

    companion object {
        @Volatile
        private var instance: MessageManagerImpl? = null

        fun getInstance(): MessageManagerImpl =
            instance ?: synchronized(this) {
                instance ?: MessageManagerImpl().also { instance = it }
            }
    }

    override fun sendMsg(msg: Message?) {
        val n = mCallbackListener.beginBroadcast()
        for (i in 0 until n) {
            val listener = mCallbackListener.getBroadcastItem(i)
            listener?.let {
                try {
                    it.onReceive(msg)
                } catch (e: Exception) {

                }
            }
        }
//        mCallbackListener.finishBroadcast()
    }

    override fun registerReceiveListener(listener: IMessageCallbackListener?) {
        mCallbackListener.register(listener)
    }

    override fun unRegisterReceiveListener(listener: IMessageCallbackListener?) {
        mCallbackListener.unregister(listener)
    }
}