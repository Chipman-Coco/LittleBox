package com.chipman.data.service.eyepetizer

import com.chipman.data.service.BaseService
import com.chipman.model.eyepetizer.Daily
import com.chipman.model.eyepetizer.Discovery
import com.chipman.model.eyepetizer.HomePageRecommend
import retrofit2.http.GET

interface HomeService : BaseService {

    /**
     * 首页-发现列表
     */
    @GET("api/v7/index/tab/discovery")
    suspend fun getDiscover(): Discovery

    /**
     * 首页-推荐列表
     */
    @GET("api/v5/index/tab/allRec")
    suspend fun getHomePageRecommend(): HomePageRecommend

    /**
     * 首页-日报lieb
     */
    @GET("api/v5/index/tab/feed")
    suspend fun getDaily(): Daily
}