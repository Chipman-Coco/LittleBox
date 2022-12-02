package com.chipman.littlebox.funny.api

import com.chipman.littlebox.funny.api.result.ApiResponse
import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.data.bean.BeRecommendUser
import com.chipman.littlebox.funny.data.bean.RecommendInfo
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService : BaseService {

    /**
     * 获取关注的用户发布的段子列表
     */
    @POST("home/attention/list")
    suspend fun getAttentionList(@Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 获取主页的推荐关注数据
     */
    @POST("home/attention/recommend")
    suspend fun getAttentionRecommend(): ApiResponse<List<BeRecommendUser>>

    /**
     * 搜索段子
     */
    @POST("home/jokes/search")
    suspend fun searchJokes(@Query("keyword") keyword: String, @Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 获取主页的最新列表数据
     */
    @POST("home/latest")
    suspend fun getLatestList(): ApiResponse<List<RecommendInfo>>

    /**
     * 获取主页的纯图片列表数据
     */
    @POST("home/pic")
    suspend fun getPicList(): ApiResponse<List<RecommendInfo>>

    /**
     * 获取主页的推荐列表数据
     */
    @POST("home/recommend")
    suspend fun getRecommendInfo(): ApiResponse<List<RecommendInfo>>

    /**
     * 获取主页的纯文列表数据
     */
    @POST("home/text")
    suspend fun getTextList(): ApiResponse<List<RecommendInfo>>

    /**
     * 获取划一划页面的推荐列表数据
     * tip: 此接口没有分页，当你快要滑到底部的时候再次调用此接口即可，后端会做过滤操作
     */
    @POST("douyin/list")
    suspend fun getTiktokVideoList(): ApiResponse<List<RecommendInfo>>
}