package com.chipman.data.repository.eyepetizer

import com.chipman.data.service.eyepetizer.HomeService
import com.chipman.model.eyepetizer.Discovery
import com.chipman.model.eyepetizer.HomePageRecommend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeService: HomeService
) {

    private var discoveryNextPageUrl: String = ""
    private var homeRecommendNextPageUrl: String = ""

    fun getDiscover(): Flow<List<Discovery.Item>> {
        return flow {
            val discovery = homeService.getDiscover()
            if (discovery.itemList.isEmpty()) {
                emit(emptyList())
            } else {
                discoveryNextPageUrl = discovery.nextPageUrl ?: ""
                emit(discovery.itemList)
            }
        }.catch { e ->
            Timber.e("getDiscover catch: $e")
        }
    }

    fun loadMoreDiscover(): Flow<List<Discovery.Item>> {
        return flow {
            if (discoveryNextPageUrl.isEmpty()) {
                emit(emptyList())
            } else {
                getDiscover()
            }
        }
    }


    fun getHomePageRecommend(): Flow<List<HomePageRecommend.Item>> {
        return flow {
            val recommend = homeService.getHomePageRecommend()
            if (recommend.itemList.isEmpty()) {
                emit(emptyList())
            } else {
                homeRecommendNextPageUrl = recommend.nextPageUrl ?: ""
                emit(recommend.itemList)
            }
        }
    }

    fun loadMoreRecommend(): Flow<List<HomePageRecommend.Item>> {
        return flow {
            if (homeRecommendNextPageUrl.isEmpty()) {
                emit(emptyList())
            } else getHomePageRecommend()
        }
    }
}