package com.chipman.littlebox.funny.service.impl

import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.HelperService
import javax.inject.Inject

class HelperServiceImpl @Inject constructor() : HelperService {

    private val service by lazy { RetrofitManager.getService(HelperService::class.java) }

    override suspend fun feedback(
        brand: String,
        contact: String,
        content: String,
        pics: String,
        product: String,
        releaseVersion: String,
        versionCode: String,
        versionName: String
    ) = service.feedback(brand, contact, content, pics, product, releaseVersion, versionCode, versionName)

    override suspend fun getHotSearch() = service.getHotSearch()

    override suspend fun getJokeShare(id: Int) = service.getJokeShare(id)

    override suspend fun jokeShareCount(id: Int) = service.jokeShareCount(id)

    override suspend fun getQiniuToken(fileName: String, type: Int) = service.getQiniuToken(fileName, type)

    override suspend fun getQQService() = service.getQQService()

    override suspend fun report(content: String, tips: String, targetId: Int, type: Int) =
        service.report(content, tips, targetId, type)

    override suspend fun getUserShare() = service.getUserShare()
}