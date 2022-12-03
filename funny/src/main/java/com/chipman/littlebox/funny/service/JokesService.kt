package com.chipman.littlebox.funny.service

import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.data.result.ApiResponse
import com.chipman.littlebox.funny.service.model.*
import retrofit2.http.POST
import retrofit2.http.Query

interface JokesService : BaseService {

    /**
     * 获取审核列表
     * tip: 需要登录，可以查询你正在审核或者审核失败的段子信息列表
     * @param status  状态 0 审核中 1 审核失败
     */
    @POST("jokes/audit/list")
    suspend fun getAuditList(@Query("status") status: Int, @Query("page") page: Int): ApiResponse<List<RecommendInfo>>

    /**
     * 收藏内容-取消收藏
     * @param status true 收藏 false 取消收藏
     */
    @POST("jokes/collect")
    suspend fun toggleCollect(@Query("jokeId") jokeId: Int, @Query("status") status: Boolean): ApiResponse<Any>

    /**
     * 评论段子,一级评论
     * tip: 需要登录，评论成功之后会返回当前你评论的信息，你可以拿到这个信息插入到列表指定位置
     */
    @POST("jokes/comment")
    suspend fun comment(@Query("content") content: String, @Query("jokeId") jokeId: Int): ApiResponse<CommentInfo>

    /**
     * 删除主评论
     */
    @POST("jokes/comment/delete")
    suspend fun deleteComment(@Query("commentId") commentId: Int): ApiResponse<Any>

    /**
     * 添加子评论
     */
    @POST("jokes/comment/item")
    suspend fun addCommentItem(
        @Query("commentId") commentId: Int,
        @Query("content") content: String,
        @Query("isReplyChild") isReplyChild: Boolean = false
    ): ApiResponse<SubComment>

    /**
     * 删除子评论
     */
    @POST("jokes/comment/item/delete")
    suspend fun deleteCommentItem(@Query("commentId") commentId: Int): ApiResponse<Any>

    /**
     * 评论 点赞-取消点赞
     * @param commentId 主评论id
     * @param status    true 点赞 false 取消点赞
     */
    @POST("jokes/comment/like")
    suspend fun toggleLike(@Query("commentId") commentId: Int, @Query("status") status: Boolean): ApiResponse<Any>

    /**
     * 获取评论列表
     */
    @POST("jokes/comment/list")
    suspend fun getCommentList(@Query("jokeId") jokeId: Int, @Query("page") page: Int): ApiResponse<CommentList>

    /**
     * 获取子评论列表
     * @param page 分页,示例值：1
     */
    @POST("jokes/comment/list/item")
    suspend fun getCommentItemList(@Query("commentId") commentId: Int, @Query("page") page: Int): ApiResponse<List<SubComment>>

    /**
     * 删除段子
     */
    @POST("jokes/delete")
    suspend fun deleteJokes(@Query("jokeId") jokeId: Int): ApiResponse<Any>

    /**
     * 获取收藏状态
     * tip: 用于分享弹窗中显示是否收藏
     */
    @POST("jokes/is_collect")
    suspend fun getCollectState(@Query("jokeId") jokeId: Int): ApiResponse<CollectState>

    /**
     * 给段子点赞-取消点赞
     */
    @POST("jokes/like")
    suspend fun giveLike(@Query("id") id: Int, @Query("status") status: Boolean): ApiResponse<Any>

    /**
     * 获取指定id的段子的点赞列表
     */
    @POST("jokes/like/list")
    suspend fun getLikeListByJokesId(@Query("jokeId") jokeId: Int, @Query("page") page: Int): ApiResponse<List<GiveLikeUser>>

    /**
     * 发布段子
     * tip: 需要登录，无论是图片还是视频，都需要上传到七牛云之后拿到接口返回的地址，地址是不带域名的请注意
     * @param content 内容
     * @param imageSize 图片尺寸，例如1080x2400，如果是多图，则需要拼接所有的图片尺寸，中间用,分割，比如 480x800,1080x900
     * @param imageUrls 如果type为2 此值代表图片列表，中间用,分割
     * @param type 发布类型 1 文本 2 图片 3 视频
     * @param videoDuration 视频播放时长
     * @param videoSize 视频尺寸 1080x720
     * @param thumbUrl 视频封面
     * @param videoUrl 视频url，先上传到七牛云之后拿到key
     */
    @POST("jokes/post")
    suspend fun publishJokes(
        @Query("content") content: String,
        @Query("image_size") imageSize: String,
        @Query("image_urls") imageUrls: String,
        @Query("type") type: String,
        @Query("video_duration") videoDuration: String,
        @Query("video_size") videoSize: String,
        @Query("video_thumbnail_url") thumbUrl: String,
        @Query("video_url") videoUrl: String
    ): ApiResponse<Any>

    /**
     * 获取指定id的段子
     */
    @POST("jokes/target")
    suspend fun getJokesById(@Query("jokeId") jokeId: Int): ApiResponse<RecommendInfo>

    /**
     * 获取指定用户喜欢的图文段子列表
     */
    @POST("jokes/text_pic_jokes/like/list")
    suspend fun getUserLikeTextPicJokesList(
        @Query("targetUserId") targetUserId: Int,
        @Query("page") page: Int
    ): ApiResponse<List<RecommendInfo>>

    /**
     * 获取指定用户图文段子列表
     */
    @POST("jokes/text_pic_jokes/list")
    suspend fun getTextPicJokesListByUserId(
        @Query("targetUserId") targetUserId: Int,
        @Query("page") page: Int
    ): ApiResponse<List<RecommendInfo>>

    /**
     * 踩段子-取消踩段子
     * @param status    true为踩 false为取消踩
     */
    @POST("jokes/unlike")
    suspend fun unlikeJokes(@Query("id") id: Int, @Query("status") status: Boolean): ApiResponse<Any>

    /**
     * 获取指定用户喜欢的视频列表
     */
    @POST("jokes/video/like/list")
    suspend fun getLikeVideoList(@Query("targetUserId") targetUserId: Int, @Query("page") page: Int): ApiResponse<List<UserVideo>>

    /**
     * 获取指定用户所有视频列表
     */
    @POST("jokes/video/list")
    suspend fun getVideoListByUserId(
        @Query("targetUserId") targetUserId: Int,
        @Query("page") page: Int
    ): ApiResponse<List<UserVideo>>

    /**
     * 获取指定id对应视频列表
     * tip: 目前用户实现本地记录用户浏览记录，然后通过记录的id获取信息
     * @param ids 段子id列表，中间用,分割
     */
    @POST("jokes/video/list/ids")
    suspend fun getVideoListById(@Query("ids") ids: String): ApiResponse<List<RecommendInfo>>

}