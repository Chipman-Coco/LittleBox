package com.chipman.littlebox.funny.service.impl

import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.JokesService

class JokesServiceImpl : JokesService {

    private val service by lazy { RetrofitManager.getService(JokesService::class.java) }

    override suspend fun getAuditList(status: Int, page: Int) = service.getAuditList(status, page)

    override suspend fun toggleCollect(jokeId: Int, status: Boolean) = service.toggleCollect(jokeId, status)

    override suspend fun comment(content: String, jokeId: Int) = service.comment(content, jokeId)

    override suspend fun deleteComment(commentId: Int) = service.deleteComment(commentId)

    override suspend fun addCommentItem(commentId: Int, content: String, isReplyChild: Boolean) =
        service.addCommentItem(commentId, content, isReplyChild)

    override suspend fun deleteCommentItem(commentId: Int) = service.deleteCommentItem(commentId)

    override suspend fun toggleLike(commentId: Int, status: Boolean) = service.toggleLike(commentId, status)

    override suspend fun getCommentList(jokeId: Int, page: Int) = service.getCommentList(jokeId, page)

    override suspend fun getCommentItemList(commentId: Int, page: Int) = service.getCommentItemList(commentId, page)

    override suspend fun deleteJokes(jokeId: Int) = service.deleteJokes(jokeId)

    override suspend fun getCollectState(jokeId: Int) = service.getCollectState(jokeId)

    override suspend fun giveLike(id: Int, status: Boolean) = service.giveLike(id, status)

    override suspend fun getLikeListByJokesId(jokeId: Int, page: Int) = service.getLikeListByJokesId(jokeId, page)

    override suspend fun publishJokes(
        content: String,
        imageSize: String,
        imageUrls: String,
        type: String,
        videoDuration: String,
        videoSize: String,
        thumbUrl: String,
        videoUrl: String
    ) = service.publishJokes(content, imageSize, imageUrls, type, videoDuration, videoSize, thumbUrl, videoUrl)

    override suspend fun getJokesById(jokeId: Int) = service.getJokesById(jokeId)

    override suspend fun getUserLikeTextPicJokesList(targetUserId: Int, page: Int) =
        service.getUserLikeTextPicJokesList(targetUserId, page)

    override suspend fun getTextPicJokesListByUserId(targetUserId: Int, page: Int) =
        service.getTextPicJokesListByUserId(targetUserId, page)

    override suspend fun unlikeJokes(id: Int, status: Boolean) = service.unlikeJokes(id, status)

    override suspend fun getLikeVideoList(targetUserId: Int, page: Int) = service.getLikeVideoList(targetUserId, page)

    override suspend fun getVideoListByUserId(targetUserId: Int, page: Int) = service.getVideoListByUserId(targetUserId, page)

    override suspend fun getVideoListById(ids: String) = service.getVideoListById(ids)
}