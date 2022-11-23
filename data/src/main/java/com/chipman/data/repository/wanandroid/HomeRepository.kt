package com.chipman.data.repository.wanandroid

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.chipman.common.base.BaseViewModel
import com.chipman.common.result.getOrElse
import com.chipman.common.result.getOrNull
import com.chipman.data.service.BaseService
import com.chipman.data.service.wanandroid.HomeService
import com.chipman.data.source.IntKeyPagingSource
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * 首页Tab数据仓库
 */
class HomeRepository @Inject constructor(
    private val homeService: HomeService
) {

    private suspend fun getBanner() = homeService.getBanner()

    private suspend fun getArticleTopList() = homeService.getArticleTopList()

    /**
     * 获取文章页数据
     * @param pageSize Int
     * @return Flow<PagingData<Any>>
     */
    fun getArticlePageList(pageSize: Int) =
        Pager(
            PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(
                BaseService.DEFAULT_PAGE_START_NUM,
                service = homeService
            ) { service, page, pageSize ->
                // 根据当前页码判断是否需要获取Banner和置顶文章
                if (page == BaseService.DEFAULT_PAGE_START_NUM) {
                    val (articleDeferred, topsDeferred, bannersDeferred) =
                        supervisorScope {
                            Triple(
                                async { service.getArticlePageList(page, pageSize) },
                                async { getArticleTopList() },
                                async { getBanner() }
                            )
                        }
                    val articles = articleDeferred.await().getOrNull()?.datas ?: emptyList()
                    val tops = topsDeferred.await().getOrElse { emptyList() }
                    val banners = bannersDeferred.await().getOrElse { emptyList() }
                    with(ArrayList<Any>(1 + tops.size + articles.size)) {
//                        if (banners.isNotEmpty()) {
//                            add(Banners(banners))
//                        }
                        addAll(tops)
                        addAll(articles)
                        this
                    }
                } else {
                    // 加载更多
                    service.getArticlePageList(page, pageSize).getOrNull()?.datas ?: emptyList()
                }
            }
        }.flow

    /**
     * 获取广场页数据
     * @param pageSize Int
     * @return Flow<PagingData<Article>>
     */
    fun getSquarePageList(pageSize: Int) =
        Pager(
            PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(
                BaseService.DEFAULT_PAGE_START_NUM,
                service = homeService
            ) { service, page, pageSize ->
                service.getSquarePageList(page, pageSize).getOrNull()?.datas ?: emptyList()
            }
        }.flow

    /**
     * 获取问题页数据
     * @return Flow<PagingData<Article>>
     */
    fun getAnswerPageList() =
        Pager(
            PagingConfig(
                pageSize = BaseViewModel.DEFAULT_PAGE_SIZE,
                initialLoadSize = BaseViewModel.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(
                BaseService.DEFAULT_PAGE_START_NUM_1,
                service = homeService
            ) { service, page, _ ->
                service.getAnswerPageList(page).getOrNull()?.datas ?: emptyList()
            }
        }.flow
}