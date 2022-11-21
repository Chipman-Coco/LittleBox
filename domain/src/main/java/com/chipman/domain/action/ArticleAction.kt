package com.chipman.domain.action

import com.chipman.model.wanandroid.Article

sealed interface ArticleAction {

    /**
     * item点击事件
     */
    data class ItemClick(val position: Int, val article: Article) : ArticleAction

    /**
     * 收藏点击事件
     */
    data class CollectClick(val position: Int, val article: Article) : ArticleAction

    /**
     * 作者点击事件
     */
    data class AuthorClick(val position: Int, val article: Article) : ArticleAction
}