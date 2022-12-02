package com.chipman.littlebox.funny.data.bean

/**
 * 段子评论信息
 */
data class CommentInfo(
    val commentId: Int,                     // 评论id，删除评论需要
    val commentUser: UserInfo,              // 发起评论的用户
    val content: String,                    // 你评论的内容
    val isLike: Boolean,                    // 你是否点赞过此评论
    val itemCommentList: List<SubComment>,  // 子评论列表
    val itemCommentNum: Int,                // 子评论的数量
    val jokeId: Int,                        // 被评论的段子id
    val jokeOwnerUserId: Int,               // 段子所属的用户
    val likeNum: Int,                       // 评论的点赞数
    val timeStr: String,                    // 评论时间戳，已格式化
)

/**
 * 子评论项
 */
data class SubComment(
    val commentItemId: Int,         //子评论id
    val commentParentId: Int,       //主评论id
    val commentUser: User_1,        //发起评论的用户
    val commentedNickname: String,  //被评论的用户昵称
    val commentedUserId: Int,       //被评论的用户id
    val content: String,            //评论内容
    val isReplyChild: Boolean,      //是否是回复子评论的
    val jokeId: Int,                //段子id
    val timeStr: String,            //评论时间，已经格式化
)

/**
 * 评论列表
 */
data class CommentList(
    val comments: List<CommentInfo>,    // 评论项
    val count: Int                      // 评论数量
)