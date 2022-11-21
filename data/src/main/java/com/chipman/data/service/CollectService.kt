package com.chipman.data.service

import com.chipman.common.result.ApiResponse
import com.chipman.model.wanandroid.CollectBean
import com.chipman.model.wanandroid.PageResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectService : BaseService {

    /**
     * 收藏列表
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): ApiResponse<PageResponse<CollectBean>>

    /**
     * 收藏站内文章
     */
    @POST("lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): ApiResponse<Any>

    /**
     * 取消收藏站内文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollectArticle(@Path("id") id: Int): ApiResponse<Any>

    suspend fun toggleCollectArticle(collect: Boolean, id: Int) =
        if (collect) collectArticle(id) else unCollectArticle(id)

}