package com.chipman.littlebox.funny.service.impl

import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.HomeService
import javax.inject.Inject

class HomeServiceImpl @Inject constructor() : HomeService {

    private val service by lazy { RetrofitManager.getService(HomeService::class.java) }

    override suspend fun getAttentionList(page: Int) = service.getAttentionList(page)

    override suspend fun getAttentionRecommend() = service.getAttentionRecommend()

    override suspend fun searchJokes(keyword: String, page: Int) = service.searchJokes(keyword, page)

    override suspend fun getLatestList() = service.getLatestList()

    override suspend fun getPicList() = service.getPicList()

    override suspend fun getRecommendInfo() = service.getRecommendInfo()

    override suspend fun getTextList() = service.getTextList()

    override suspend fun getTiktokVideoList() = service.getTiktokVideoList()
}