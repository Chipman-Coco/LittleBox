package com.chipman.model.wanandroid

/**
 * 个人基本信息
 */
data class UserBaseInfo(
    val coinInfo: CoinInfo = CoinInfo(),
    val collectArticleInfo: CollectArticleInfo = CollectArticleInfo(),
    val userInfo: User = User()
)