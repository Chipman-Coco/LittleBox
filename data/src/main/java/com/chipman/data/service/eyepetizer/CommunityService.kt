package com.chipman.data.service.eyepetizer

import com.chipman.data.service.BaseService
import com.chipman.model.eyepetizer.CommunityRecommend
import com.chipman.model.eyepetizer.Follow
import retrofit2.http.GET

interface CommunityService : BaseService {

    /**
     * 社区-推荐列表
     */
    @GET("api/v7/community/tab/rec")
    suspend fun getCommunityRecommend(): CommunityRecommend

    /**
     * 社区-关注列表
     */
    @GET("api/v6/community/tab/follow")
    suspend fun getFollow(): Follow
}