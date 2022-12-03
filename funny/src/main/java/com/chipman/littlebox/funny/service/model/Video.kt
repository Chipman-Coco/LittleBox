package com.chipman.littlebox.funny.service.model

/**
 * 用户视频信息
 */
data class UserVideo(
    val cover: String,      // 视频封面
    val jokeId: Int,        // 段子id
    val likeNum: String,    // 点赞数量，已经格式化
)

/**
 * 视频下载权限信息
 */
data class VideoPermission(
    val state: Boolean,     // 是否可以下载
    val tips: String        // 不可以下载时候的提示信息
)
