package com.chipman.player.player

import android.view.Surface
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData
import com.chipman.player.bean.PlayerState
import com.chipman.player.bean.VideoInfo
import com.chipman.player.bean.VideoSize

interface IMediaPlayer<T> {

    /** 播放器实例 */
    var player: T

    /** 准备后是否播放 */
    var playWhenReady: Boolean

    /** 是否正在播放 */
    val isPlaying: Boolean

    /** 当前播放位置 */
    val currentPosition: Long

    /** 视频长度 */
    val duration: Long

    /** 视频高度 */
    val videoHeight: Int

    /** 视频宽度 */
    val videoWith: Int

    /** 当前播放状态 */
    val playerState: PlayerState

    /** 播放器状态监听 */
    val playerStateLD: LiveData<PlayerState>

    /** 播放器尺寸 */
    val videoSizeLD: LiveData<VideoSize>

    /** 加载缓存进度 */
    val bufferProgressLD: LiveData<Int>

    /** 是否跳转完成 */
    val seekCompleteLD: LiveData<Boolean>

    /** 视频信息 */
    val videoInfoLD: LiveData<VideoInfo>

    /** 视频报错 */
    val videoErrorLD: LiveData<VideoInfo>

    /** 开始播放 */
    fun start()

    /** 准备 */
    fun prepare()

    /** 异步准备 */
    fun prepareAsync()

    /** 暂停 */
    fun pause()

    /** 停止 */
    fun stop()

    /** 跳转到指定位置 */
    fun seekTo(time: Long)

    /** 重置 */
    fun reset()

    /** 释放资源 */
    fun release()

    /** 设置音量 */
    fun setVolume(volume: Float)

    /** 设置循环播放 */
    fun setLooping(isLoop: Boolean)

    /** 设置播放容器 */
    fun setSurface(surface: Surface?)

    /** 设置播放容器 */
    fun setDisplay(surfaceHolder: SurfaceHolder)

}