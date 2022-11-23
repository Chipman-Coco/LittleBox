package com.chipman.domain.account

import com.chipman.common.result.ApiResponse
import com.chipman.data.service.wanandroid.AccountService
import com.chipman.model.wanandroid.User
import com.chipman.model.wanandroid.UserBaseInfo
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface AccountRepository {

    val accountState: StateFlow<AccountState>

    val accountInfo: StateFlow<UserBaseInfo>

    val isLogin: Boolean

    val userId: String

    suspend fun login(username: String, password: String): ApiResponse<User>

    suspend fun logout(): ApiResponse<Any>

    suspend fun register(username: String, password: String, confirmPassword: String): ApiResponse<Any>

    suspend fun getUserInfo(): ApiResponse<UserBaseInfo>
}

class WanAccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
    private val accountManager: AccountManager
) : AccountRepository {

    override val accountState: StateFlow<AccountState>
        get() = accountManager.accountStateFlow()

    override val accountInfo: StateFlow<UserBaseInfo>
        get() = accountManager.collectUserInfoFlow()

    override val isLogin: Boolean
        get() = accountManager.isLogin()

    override val userId: String
        get() = accountInfo.value.userInfo.id

    override suspend fun login(username: String, password: String): ApiResponse<User> {
        return accountService.login(username, password)
    }

    override suspend fun logout(): ApiResponse<Any> {
        return accountService.logout()
    }

    override suspend fun register(username: String, password: String, confirmPassword: String): ApiResponse<Any> {
        return accountService.register(username, password, confirmPassword)
    }

    override suspend fun getUserInfo(): ApiResponse<UserBaseInfo> {
        return accountService.getUserInfo()
    }
}