package com.chipman.littlebox.funny.service.model

import com.google.gson.annotations.SerializedName

data class User(
    val avatar: String,
    val nickName: String,
    val signature: String, // 用户签名信息
    val userId: Int
)

data class User_1(
    val nickname: String,
    val userAvatar: String,
    val userId: Int
)

/**
 * 用户乐豆信息
 */
data class Ledou(
    val experienceNum: String,      // 经验值
    val experienceRank: String,     // 经验等级
    val isSignin: Boolean,          // 是否签到
    val lotteryCost: Int,           // 签到积分数量
)

/**
 * 指定用户主页信息
 */
data class AssignUserHome(
    val attentionNum: String,       // 关注的数量 已经格式化
    val attentionState: Int,        // 关注状态 0 没有关注 1 他关注我了 2 我关注他了 3 相互关注
    val avatar: String,
    val collectNum: String,         // 收藏的数量 已经格式化
    val commentNum: String,         // 评论的数量 已经格式化
    val cover: String,              // 用户封面
    val fansNum: String,            // 粉丝数量 已经格式化
    val joinTime: String,           // 入驻天数 已经格式化
    val jokeLikeNum: String,        // 喜欢的段子数量 已经格式化
    val jokesNum: String,           // 发布段子的数量 已经格式化
    val likeNum: String,            // 获得赞数量 已经格式化
    val nickname: String,
    val sigbature: String,          // 用户签名 sigbature ???
    val userId: Int
)

/**
 * 点赞用户信息
 */
data class GiveLikeUser(
    val avatar: String,
    val nickname: String,
    @SerializedName("user_id")
    val userId: Int
)
