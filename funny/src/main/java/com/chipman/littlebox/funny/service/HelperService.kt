package com.chipman.littlebox.funny.service

import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.data.result.ApiResponse
import com.chipman.littlebox.funny.service.model.QQInfo
import com.chipman.littlebox.funny.service.model.QiniuUploadInfo
import com.chipman.littlebox.funny.service.model.SharedInfo
import retrofit2.http.POST
import retrofit2.http.Query

interface HelperService : BaseService {

    /**
     * 意见反馈
     * @param brand 设备品牌
     * @param contact 联系方式
     * @param content 反馈的内容
     * @param pics 图片链接，多图用,分割，图片请先上传至七牛云
     * @param product 设备型号
     * @param releaseVersion 系统版本号
     * @param versionCode 当前版本code
     * @param versionName 当前版本号
     */
    @POST("helper/feedback")
    suspend fun feedback(
        @Query("brand") brand: String,
        @Query("contact") contact: String,
        @Query("content") content: String,
        @Query("pics") pics: String,
        @Query("product") product: String,
        @Query("release_version") releaseVersion: String,
        @Query("version_code") versionCode: String,
        @Query("version_name") versionName: String
    ): ApiResponse<Any>

    /**
     * 获取热搜关键词
     */
    @POST("helper/hot_search")
    suspend fun getHotSearch(): ApiResponse<List<String>>

    /**
     * 获取jokes分享的内容
     */
    @POST("helper/joke/share")
    suspend fun getJokeShare(@Query("id") id: Int): ApiResponse<SharedInfo>

    /**
     * 段子分享成功计数
     */
    @POST("helper/joke/share/count")
    suspend fun jokeShareCount(@Query("id") id: Int): ApiResponse<Any>

    /**
     * 获取七牛云token
     * @param fileName 文件名称，要带后缀
     * @param type  0 获取普通token  1 获取头像token
     */
    @POST("helper/qiniu/token")
    suspend fun getQiniuToken(
        @Query("filename") fileName: String,
        @Query("type") type: Int
    ): ApiResponse<QiniuUploadInfo>

    /**
     * 获取qq群信息
     */
    @POST("helper/qq_service")
    suspend fun getQQService(): ApiResponse<QQInfo>

    /**
     * 举报内容
     * tip: 举报用户或者举报段子
     * @param content 举报的内容
     * @param tips 举报描述信息，预置的选择项
     * @param targetId  0 段子id 1 用户id
     * @param type 0 举报段子 1 举报用户
     */
    @POST("helper/report")
    suspend fun report(
        @Query("content") content: String,
        @Query("report_tips") tips: String,
        @Query("target_id") targetId: Int,
        @Query("type") type: Int
    ): ApiResponse<Any>

    /**
     * 获取分享用户的内容
     */
    @POST("helper/user/share")
    suspend fun getUserShare(): ApiResponse<SharedInfo>
}