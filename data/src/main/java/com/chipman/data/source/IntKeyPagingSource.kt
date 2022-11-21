package com.chipman.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chipman.data.service.BaseService

class IntKeyPagingSource<S : BaseService, R : Any>(
    private val pageStartNum: Int = BaseService.DEFAULT_PAGE_START_NUM_1,
    private val service: S,
    private val load: suspend (S, Int, Int) -> List<R>
) : PagingSource<Int, R>() {

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        return try {
            val page = params.key ?: pageStartNum
            val response = load(service, page, params.loadSize)
            LoadResult.Page(data = response, prevKey = null, nextKey = if (response.isEmpty()) null else page + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}