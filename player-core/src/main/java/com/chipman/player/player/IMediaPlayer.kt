package com.chipman.player.player

interface IMediaPlayer<T> {

    /**
     * 播放器实例
     */
    var player: T

    /**
     * 准备后是否播放
     */
    var playWhenReady: Boolean

    /**
     *  是否正在播放
     */
    val isPlaying: Boolean

    /**
     * 当前播放位置
     */
    val currentPosition: Long

    /**
     * 视频长度
     */
    val duration: Long

    /**
     * 视频高度
     */
    val videoHeight: Int

    /**
     * 视频宽度
     */
    val videoWith: Int

    /**
     * 当前播放状态
     */

}