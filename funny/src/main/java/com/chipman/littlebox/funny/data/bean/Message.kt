package com.chipman.littlebox.funny.data.bean

/**
 * 用户关注信息
 */
data class UserAttentionMsg(
    val attention: Int,         //关注状态 0 没有关注 1 他关注我了 2 我关注他了 3 相互关注
    val avatar: String,
    val nickname: String,
    val signature: String,      //用户签名信息
    val userId: Int
)

/**
 * 用户未读消息数量信息
 */
data class UnreadMsg(
    val attentionMum: Int,              // 关注数量
    val commentNum: Int,                // 评论数量
    val likeNum: Int,                   // 点赞数量
    val systemLatestContent: String,    // 最后一条系统消息内容
    val systemNum: Int,                 // 系统消息未读数
)

/**
 * 用户消息信息
 */
data class UserMsg(
    val commentId: Int,             // 评论id
    val content: String,            // 消息内容
    val extraContent: String,       // 消息附加内容
    val msgId: Int,                 // 消息id
    val msgItemType: Int,           // 消息子类型 10 赞内容 11 踩内容 12 赞评论 20 评论内容 21,22 回复评论 30 关注你
    val msgItemTypeDesc: String,    // 消息子类型描述
    val msgMainType: Int,           // 消息主类型，1 - 赞 · 踩 2 -> 评论 3 -> 关注
    val msgMainTypeDesc: String,    // 消息主类型描述
    val msgStatus: Int,             //消息状态 0 未读 1 已读
    val msgTime: String,            //消息创建的时间
    val ownerUserId: Int,           //消息所属用户id
    val targetId: Int,              //消息目标id
    val targetNickname: String,     //消息发起者昵称
    val targetUserAvatar: String,   //消息发起者头像
    val targetUserId: Int,          //消息发起者id
)

/**
 * 系统消息
 */
data class SystemMsg(
    val content: String,    // 消息内容
    val id: Int,            // 消息id
    val isRead: Boolean,    // 是否已读
    val targetId: Int,      // 消息目标id
    val timeStr: String,    // 消息时间戳
    val title: String,      // 消息标题
    val type: Int,          // 消息类型 1 注册成功 2 举报用户提醒 3 举报段子提醒 4 审核成功结果通知 5审核失败 6 意见反馈
)