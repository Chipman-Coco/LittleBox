package com.chipman.littlebox.funny.service.model

/**
 * 首页推荐通用数据
 */
data class RecommendInfo(
    val info: JokeInfo,     // 段子社交信息
    val joke: Joke,         // 段子内容
    val user: User,         // 所属用户
)