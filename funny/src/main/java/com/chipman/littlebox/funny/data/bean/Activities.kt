package com.chipman.littlebox.funny.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 积分项数据
 */
data class Integrate(
    val desc: String,   // 描述
    val num: String,    // 积分数量
    val time: String,   // 时间
)

/**
 * 签到奖励信息
 */
data class SigninAward(
    val num: Int    // 签到奖励数量
)

/**
 * 通用分享数据
 */
data class SharedInfo(
    @SerializedName("share_content") val shareContent: String,      // 分享的内容
    @SerializedName("share_img") val shareImg: String,              // 分享的图片链接
    @SerializedName("share_title") val shareTitle: String,          // 分享的标题
    @SerializedName("share_url") val shareUrl: String,              // 分享的url
)
