package com.chipman.data.service.wanandroid

import com.chipman.common.result.ApiResponse
import com.chipman.data.service.BaseService
import com.chipman.model.wanandroid.User
import com.chipman.model.wanandroid.UserBaseInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService : BaseService {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse<User>

    /**
     * 退出
     */
    @GET("user/logout/json")
    suspend fun logout(): ApiResponse<Any>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") confirmPassword: String
    ): ApiResponse<Any>

    /**
     * 获取用户信息
     */
    @GET("user/lg/userinfo/json")
    suspend fun getUserInfo(): ApiResponse<UserBaseInfo>
}