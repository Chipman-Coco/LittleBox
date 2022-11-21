package com.chipman.data.repository

import com.chipman.common.result.ApiResponse
import com.chipman.data.service.AccountService
import com.chipman.model.wanandroid.User
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountService: AccountService
) {

    suspend fun login(username: String,password: String): ApiResponse<User> {
        return accountService.login(username,password)
    }

    suspend fun logout() = accountService.logout()

    suspend fun register(
        username: String,
        password: String,
        confirmPassword: String
    ) = accountService.register(username,password,confirmPassword)

    suspend fun getUserInfo() = accountService.getUserInfo()
}