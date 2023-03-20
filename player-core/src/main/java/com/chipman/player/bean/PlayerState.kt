package com.chipman.player.bean

/**
 * 播放器状态
 */
sealed class PlayerState(val code: Int) {

    /** 空闲 */
    object IDLE : PlayerState(1 shl 1)

    /** 初始化 */
    object INITIALIZE : PlayerState(1 shl 2)

    /** 准备中 */
    object PREPARING : PlayerState(1 shl 3)

    /** 准备完成 */
    object PREPARED : PlayerState(1 shl 4)

    /** 已开始 */
    object STARTED : PlayerState(1 shl 5)

    /** 已暂停 */
    object PAUSED : PlayerState(1 shl 6)

    /** 已停止 */
    object STOPPED : PlayerState(1 shl 7)

    /** 已完成 */
    object COMPLETED : PlayerState(1 shl 8)

    /** 异常 */
    object ERROR : PlayerState(1 shl 9)

    /** 释放 */
    object END : PlayerState(1 shl 10)

}
