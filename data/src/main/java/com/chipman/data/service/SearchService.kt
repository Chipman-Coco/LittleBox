package com.chipman.data.service

import com.chipman.common.result.ApiResponse
import com.chipman.model.wanandroid.Article
import com.chipman.model.wanandroid.HotKeyBean
import com.chipman.model.wanandroid.PageResponse
import retrofit2.http.*

interface SearchService : BaseService {

    /**
     * 热搜词
     */
    @GET("hotkey/json")
    suspend fun getSearchHotKey(): ApiResponse<List<HotKeyBean>>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun queryBySearchKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): ApiResponse<PageResponse<Article>>
}