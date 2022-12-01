package com.chipman.data.service.eyepetizer

import com.chipman.data.service.BaseService
import com.chipman.model.eyepetizer.PushMessage
import retrofit2.http.GET

interface NotificationService : BaseService {

    /**
     * 通知-推送列表
     */
    @GET("api/v3/messages")
    suspend fun getPushMessage(): PushMessage
}