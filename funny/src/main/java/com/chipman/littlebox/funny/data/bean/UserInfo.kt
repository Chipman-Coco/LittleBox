package com.chipman.littlebox.funny.data.bean

/**
 * 用户信息
 */
data class UserInfo(
    val nickname: String,
    val userAvatar: String,
    val userId: Int
)

/**
 * 用户信息_1
 */
data class UserInfo_1(
    val avatar: String,
    val birthday: String,       //用户生日
    val inviteCode: String,     //用户邀请码
    val invitedCode: String,    //用户被邀请码
    val nickname: String,
    val sex: String,
    val signature: String,      //用户签名
    val userId: Int,
    val userPhone: String       //用户手机号 打码过的
)

/**
 * 用户登录成功信息
 */
data class LoginSucUserInfo(
    val token: String,          // 用户token
    val type: String,           // 登录状态 login_success 为登录成功 register_success为注册成功
    val userInfo: UserInfo_1,   // 用户信息
)

/**
 * 用户主页信息
 */
data class UserHome(
    val info: UserSocialInfo,       // 用户社交信息
    val user: UserInfo_1,           // 用户信息
)

/**
 * 用户社交信息
 */
data class UserSocialInfo(
    val attentionNum: Int,  // 关注数量
    val experienceNum: Int, // 经验值
    val fansNum: Int,       // 粉丝数量
    val likeNum: Int,       // 喜欢数量
)

/**
 * 被推荐的用户信息
 */
data class BeRecommendUser(
    val avatar: String,         // 用户头像
    val fansNum: String,        // 粉丝数
    val isAttention: Boolean,   // 你是否已经关注
    val jokesNum: String,       // 发布的段子数量
    val nickname: String,       // 昵称
    val userId: Int,            // 用户id
)
