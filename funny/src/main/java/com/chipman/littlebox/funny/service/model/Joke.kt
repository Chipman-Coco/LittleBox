package com.chipman.littlebox.funny.service.model

import com.google.gson.annotations.SerializedName

/**
 * 段子内容信息
 */
data class Joke(
    val addTime: String,            // 添加时间，已经格式化完成
    @SerializedName("audit_msg")
    val auditMsg: String,           // 审核失败信息
    val content: String,            // 段子内容
    val hot: Boolean,               // 是否是热门
    val imageSize: String,          // 图片尺寸信息
    val imageUrl: String,           // type=2 此值有数据，多图用,分割，最多9张图
    val jokesId: Int,               // 段子id
    val latitudeLongitude: String,  // 发布时候经纬度信息
    val showAddress: String,        // 发布时候的位置信息
    val thumbUrl: String,           // 视频封面
    val type: Int,                  // 段子类型 1 文本 2 图片 >=3 视频
    val userId: Int,                // 段子所属用户id
    val videoSize: String,          // 视频尺寸
    val videoTime: Int,             // 视频时长
    val videoUrl: String            // 视频地址
)

/**
 * 段子社交信息
 */
data class JokeInfo(
    val commentNum: Int,        //评论数
    val disLikeNum: Int,        //踩的数量
    val isAttention: Boolean,   //是否关注
    val isLike: Boolean,        //是否喜欢
    val isUnlike: Boolean,      //是否踩
    val likeNum: Int,           //点赞数
    val shareNum: Int           //分享数
)