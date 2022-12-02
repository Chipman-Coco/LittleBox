package com.chipman.littlebox.funny.api

import com.chipman.littlebox.funny.api.result.ApiResponse
import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.data.bean.*
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService : BaseService {

    /**
     * 用户关注。
     * tip:需要登录
     * @param status Int  1 关注 0 取消关注
     */
    @POST("user/attention")
    suspend fun attention(@Query("status") status: Int, @Query("userId") userId: Int): ApiResponse<Any>

    /**
     * 获取指定用户关注列表
     */
    @POST("user/attention/list")
    suspend fun getAttentionListById(
        @Query("page") page: Int,
        @Query("targetUserId") targetUserId: Int
    ): ApiResponse<List<UserAttentionMsg>>

    /**
     * 绑定邀请码
     * tip：需要登录
     */
    @POST("user/bind/invite")
    suspend fun bindInvite(@Query("code") code: String): ApiResponse<Any>

    /**
     * 检查视频下载权限
     * tip:视频下载之前调用接口检查权限
     */
    @POST("user/check/permission/download")
    suspend fun checkPermissionDownload(): ApiResponse<VideoPermission>

    /**
     * 注销账户
     * tip: 需要登录
     */
    @POST("user/close_account")
    suspend fun closeAccount(@Query("psw") psw: String): ApiResponse<Any>

    /**
     * 获取当前登录用户收藏列表
     */
    @POST("user/collect/list")
    suspend fun getCollectList(@Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 获取指定用户的评论列表
     */
    @POST("user/comment/list")
    suspend fun getCommentList(@Query("page") page: Int): ApiResponse<List<UserMsg>>

    /**
     * 获取指定用户粉丝列表
     */
    @POST("user/fans/list")
    suspend fun getFansList(
        @Query("page") page: Int,
        @Query("targetUserId") targetUserId: Int
    ): ApiResponse<List<UserAttentionMsg>>

    /**
     * 获取用户信息
     */
    @POST("user/info")
    suspend fun getUserInfo(): ApiResponse<UserHome>

    /**
     * 获取指定用户信息
     */
    @POST("user/info/target")
    suspend fun getTargetUserInfo(@Query("targetUserId") targetUserId: Int): ApiResponse<AssignUserHome>

    /**
     * 更新用户信息
     * tip：需要登录
     */
    @POST("user/info/update")
    suspend fun updateUserInfo(@Query("content") content: String, @Query("type") type: Int): ApiResponse<Any>

    /**
     * 获取当前用户帖子列表
     */
    @POST("user/jokes/list")
    suspend fun getJokesList(@Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 获取当前用户积分详情
     */
    @POST("user/ledou/info")
    suspend fun getLedouInfo(): ApiResponse<Ledou>

    /**
     * 获取当前用户积分详情列表
     */
    @POST("user/ledou/list")
    suspend fun getIntegrateList(@Query("page") page: Int): ApiResponse<List<Integrate>>

    /**
     * 当前用户抽奖
     */
    @POST("user/ledou/lottery")
    suspend fun lottery(): ApiResponse<Any>

    /**
     * 当前用户抽奖列表
     */
    @POST("user/ledou/lottery/list")
    suspend fun getLotteryList(@Query("page") page: Int): ApiResponse<List<Integrate>>

    /**
     * 获取当前登录用户点赞列表
     */
    @POST("user/like/list")
    suspend fun getGiveLikeList(@Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 验证码登录
     */
    @POST("user/login/code")
    suspend fun loginCode(@Query("code") code: String, @Query("phone") phone: String): ApiResponse<LoginSucUserInfo>

    /**
     * 获取登录验证码
     * tip: 不会真实发送验证码，如需发送真实环境下的验证码，请联系管理员开通
     */
    @POST("user/login/get_code")
    suspend fun getCode(@Query("phone") phone: String): ApiResponse<Any>

    /**
     * 账号密码登录
     */
    @POST("user/login/psw")
    suspend fun loginPsw(@Query("phone") phone: String, @Query("psw") psw: String): ApiResponse<LoginSucUserInfo>

    /**
     * 获取指定用户的消息列表
     */
    @POST("user/message/list")
    suspend fun getTargetUserMsgList(@Query("page") page: Int, @Query("type") type: Int): ApiResponse<List<UserMsg>>

    /**
     * 获取系统消息列表
     * tip:需要登录
     */
    @POST("user/message/system")
    suspend fun getSystemMsgList(@Query("page") page: Int): ApiResponse<List<SystemMsg>>

    /**
     * 获取当前用户的未读消息数
     */
    @POST("user/message/unread")
    suspend fun getTargetUserUnreadMsg(): ApiResponse<UnreadMsg>

    /**
     * 修改密码
     * tip: 需要登录，修改成功之后请让用户重新走登录流程
     */
    @POST("user/psw/change")
    suspend fun changePsw(
        @Query("old_psw") oldPsw: String,
        password: String,
        @Query("new_psw") newPsw: String
    ): ApiResponse<Any>

    /**
     * 重置密码
     * tip: 密码明文传输，后台加密
     */
    @POST("user/psw/reset")
    suspend fun resetPsw(
        @Query("code") code: String,
        @Query("password") password: String,
        @Query("phone") phone: String
    ): ApiResponse<Any>

    /**
     * 重置密码获取验证码
     */
    @POST("user/psw/reset/get_code")
    suspend fun getResetPswCode(@Query("phone") phone: String): ApiResponse<Any>

    /**
     * 搜索用户
     */
    @POST("user/search")
    suspend fun searchUser(@Query("keyword") keyword: String, @Query("page") page: Int): ApiResponse<List<UserAttentionMsg>>

    /**
     * 当前用户签到
     */
    @POST("user/signin")
    suspend fun signin(): ApiResponse<SigninAward>

}