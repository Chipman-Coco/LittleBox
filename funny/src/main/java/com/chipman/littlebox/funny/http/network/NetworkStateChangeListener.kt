package com.chipman.littlebox.funny.http.network

/**
 * 网络状态改变监听起
 */
interface NetworkStateChangeListener {

    /**
     * 网络类型更改回调
     * @param type NetworkTypeEnum 网络类型
     */
    fun networkTypeChange(type: NetworkTypeEnum)

    /**
     * 网络连接状态更改回调
     * @param isConnected Boolean 是否已连接
     */
    fun networkConnectChange(isConnected: Boolean)
}