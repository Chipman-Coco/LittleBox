package com.chipman.littlebox.funny.service.impl

import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.UserService

class UserServiceImpl : UserService {

    private val service by lazy { RetrofitManager.getService(UserService::class.java) }

    override suspend fun attention(status: Int, userId: Int) = service.attention(status, userId)

    override suspend fun getAttentionListById(page: Int, targetUserId: Int) = service.getAttentionListById(page, targetUserId)

    override suspend fun bindInvite(code: String) = service.bindInvite(code)

    override suspend fun checkPermissionDownload() = service.checkPermissionDownload()

    override suspend fun closeAccount(psw: String) = service.closeAccount(psw)

    override suspend fun getCollectList(page: Int) = service.getCollectList(page)

    override suspend fun getCommentList(page: Int) = service.getCommentList(page)

    override suspend fun getFansList(page: Int, targetUserId: Int) = service.getFansList(page, targetUserId)

    override suspend fun getUserInfo() = service.getUserInfo()

    override suspend fun getTargetUserInfo(targetUserId: Int) = service.getTargetUserInfo(targetUserId)

    override suspend fun updateUserInfo(content: String, type: Int) = service.updateUserInfo(content, type)

    override suspend fun getJokesList(page: Int) = service.getJokesList(page)

    override suspend fun getLedouInfo() = service.getLedouInfo()

    override suspend fun getIntegrateList(page: Int) = service.getIntegrateList(page)

    override suspend fun lottery() = service.lottery()

    override suspend fun getLotteryList(page: Int) = service.getLotteryList(page)

    override suspend fun getGiveLikeList(page: Int) = service.getGiveLikeList(page)

    override suspend fun loginCode(code: String, phone: String) = service.loginCode(code, phone)

    override suspend fun getCode(phone: String) = service.getCode(phone)

    override suspend fun loginPsw(phone: String, psw: String) = service.loginPsw(phone, psw)

    override suspend fun getTargetUserMsgList(page: Int, type: Int) = service.getTargetUserMsgList(page, type)

    override suspend fun getSystemMsgList(page: Int) = service.getSystemMsgList(page)

    override suspend fun getTargetUserUnreadMsg() = service.getTargetUserUnreadMsg()

    override suspend fun changePsw(oldPsw: String, password: String, newPsw: String) = service.changePsw(oldPsw, password, newPsw)

    override suspend fun resetPsw(code: String, password: String, phone: String) = service.resetPsw(code, password, phone)

    override suspend fun getResetPswCode(phone: String) = service.getResetPswCode(phone)

    override suspend fun searchUser(keyword: String, page: Int) = service.searchUser(keyword, page)

    override suspend fun signin() = service.signin()
}