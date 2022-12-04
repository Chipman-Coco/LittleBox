package com.chipman.littlebox.funny.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.chipman.littlebox.funny.data.result.getOrNull
import com.chipman.littlebox.funny.service.HomeService
import com.chipman.littlebox.funny.widget.source.IntKeyPagingSource
import javax.inject.Inject

/**
 * 主页 Repository
 */
class HomeRepository @Inject constructor(private val service: HomeService) {

    fun getRecommendList(pageSize: Int) =
        Pager(
            PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                enablePlaceholders = false
            )
        ) {
            IntKeyPagingSource(service = service) { service, page, size ->
                service.getRecommendInfo().getOrNull() ?: emptyList()
            }
        }.flow

}