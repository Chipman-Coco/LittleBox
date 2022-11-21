package com.chipman.model.wanandroid

/**
 * 分享数据
 */
data class ShareBean(
    val coinInfo: CoinInfo,
    val shareArticles: PageResponse<Article>
)