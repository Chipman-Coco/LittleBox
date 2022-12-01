package com.chipman.data.service.eyepetizer

import com.chipman.data.service.BaseService
import retrofit2.http.GET

interface SearchService : BaseService {


    /**
     * 搜索-热搜关键词
     */
    @GET("api/v3/queries/hot")
    suspend fun getHotSearch(): List<String>
}