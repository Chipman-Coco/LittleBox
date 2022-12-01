package com.chipman.data.service.eyepetizer

import com.chipman.data.service.BaseService
import com.chipman.model.eyepetizer.VideoBeanForClient
import com.chipman.model.eyepetizer.VideoReplies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoService : BaseService {

    /**
     * 视频详情-视频信息
     */
    @GET("api/v2/video/{id}")
    suspend fun getVideoBeanForClient(@Path("id") videoId: Long): VideoBeanForClient

    /**
     * 视频详情-评论列表
     */
    @GET("api/v2/replies/video")
    suspend fun getVideoRelies(@Query("videoId") videoId: Long): VideoReplies
}