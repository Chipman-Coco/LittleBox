package com.chipman.data.service.wanandroid

import com.chipman.common.result.ApiResponse
import com.chipman.data.service.BaseService
import com.chipman.model.wanandroid.Article
import com.chipman.model.wanandroid.Classify
import com.chipman.model.wanandroid.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupService : BaseService {

    /**
     * 公众号作者列表
     */
    @GET("wxarticle/chapters/json")
    suspend fun getAuthorTitleList(): ApiResponse<List<Classify>>

    /**
     * 对应id作者的文章
     */
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getAuthorArticles(
        @Path("id") id: Int,
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int
    ): ApiResponse<PageResponse<Article>>

}