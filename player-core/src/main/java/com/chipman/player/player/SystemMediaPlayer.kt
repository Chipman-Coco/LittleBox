package com.chipman.player.player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.net.Uri
import android.os.Build
import android.view.Surface
import android.view.SurfaceHolder
import androidx.lifecycle.MutableLiveData
import com.chipman.player.bean.PlayerState
import com.chipman.player.bean.VideoInfo
import com.chipman.player.bean.VideoSize
import java.io.IOException

/**
 * 系统播放器的封装
 */
open class SystemMediaPlayer : IMediaPlayer<MediaPlayer>,
    OnPreparedListener,
    OnCompletionListener,
    OnBufferingUpdateListener,
    OnSeekCompleteListener,
    OnErrorListener,
    OnInfoListener,
    OnVideoSizeChangedListener {

    final override var player: MediaPlayer = MediaPlayer()
    override var playWhenReady: Boolean = true

    final override val playerStateLD = MutableLiveData<PlayerState>()
    override val videoSizeLD = MutableLiveData<VideoSize>()
    override val bufferProgressLD = MutableLiveData<Int>()
    override val seekCompleteLD = MutableLiveData<Boolean>()
    override val videoInfoLD = MutableLiveData<VideoInfo>()
    override val videoErrorLD = MutableLiveData<VideoInfo>()

    init {
        playerStateLD.value = PlayerState.IDLE
        player.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setScreenOnWhilePlaying(true)
            setOnPreparedListener(this@SystemMediaPlayer)
            setOnCompletionListener(this@SystemMediaPlayer)
            setOnBufferingUpdateListener(this@SystemMediaPlayer)
            setOnSeekCompleteListener(this@SystemMediaPlayer)
            setOnErrorListener(this@SystemMediaPlayer)
            setOnInfoListener(this@SystemMediaPlayer)
            setOnVideoSizeChangedListener(this@SystemMediaPlayer)
        }
    }

    @Throws(IOException::class, IllegalArgumentException::class)
    fun setDataSource(context: Context, uri: Uri) {
        player.setDataSource(context, uri)
        playerStateLD.value = PlayerState.INITIALIZE
    }

    override val isPlaying: Boolean
        get() = try {
            player.isPlaying
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override val currentPosition: Long
        get() = try {
            player.currentPosition.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    override val duration: Long
        get() = try {
            player.duration.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    override val videoHeight: Int
        get() = try {
            player.videoHeight
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    override val videoWith: Int
        get() = try {
            player.videoWidth
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }

    override val playerState: PlayerState
        get() = playerStateLD.value ?: PlayerState.IDLE

    override fun start() {
        try {
            player.start()
            playerStateLD.value = PlayerState.STARTED
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun prepare() {
        try {
            player.prepare()
            playerStateLD.value = PlayerState.PREPARED
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun prepareAsync() {
        try {
            player.prepareAsync()
            playerStateLD.value = PlayerState.PREPARING
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun pause() {
        try {
            player.pause()
            playerStateLD.value = PlayerState.PAUSED
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun stop() {
        try {
            player.stop()
            playerStateLD.value = PlayerState.STOPPED
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun seekTo(time: Long) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                player.seekTo(time, SEEK_CLOSEST)
            } else {
                player.seekTo(time.toInt())
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun reset() {
        try {
            player.reset()
            playerStateLD.value = PlayerState.IDLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun release() {
        try {
            player.release()
            playerStateLD.value = PlayerState.END
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setVolume(volume: Float) {
        try {
            player.setVolume(volume, volume)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setLooping(isLoop: Boolean) {
        try {
            player.isLooping = isLoop
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setSurface(surface: Surface?) {
        try {
            player.setSurface(surface)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setDisplay(surfaceHolder: SurfaceHolder) {
        try {
            player.setDisplay(surfaceHolder)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        playerStateLD.value = PlayerState.PREPARED
    }

    override fun onCompletion(mp: MediaPlayer?) {
        playerStateLD.value = PlayerState.COMPLETED
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        bufferProgressLD.value = percent
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
        seekCompleteLD.value = true
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        videoErrorLD.value = VideoInfo(what, extra)
        playerStateLD.value = PlayerState.ERROR
        return false
    }

    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        videoInfoLD.value = VideoInfo(what, extra)
        return false
    }

    override fun onVideoSizeChanged(mp: MediaPlayer?, width: Int, height: Int) {
        videoSizeLD.value = VideoSize(width, height)
    }

}