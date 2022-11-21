package com.chipman.domain.account

import com.chipman.common.di.ApplicationScope
import com.chipman.common.di.IoDispatcher
import com.chipman.model.wanandroid.User
import com.chipman.model.wanandroid.UserBaseInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountManager @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val userBaseInfoStateFlow: MutableStateFlow<UserBaseInfo> = MutableStateFlow(UserBaseInfo())

    private val accountStatusFlow: MutableStateFlow<AccountState> = MutableStateFlow(AccountState.LogOut)

    init {

    }

    /**
     * 用户信息
     */
    fun collectUserInfoFlow(): StateFlow<UserBaseInfo> = userBaseInfoStateFlow

    /**
     * 用户状态
     */
    fun accountStateFlow(): StateFlow<AccountState> = accountStatusFlow

    fun cacheUserBaseInfo(userBaseInfo: UserBaseInfo) {

    }

    fun clearUserBaseInfo() {

    }

    fun peekUserBaseInfo() = userBaseInfoStateFlow.value

    fun login(user: User) {
        accountStatusFlow.tryEmit(AccountState.LogIn(true, user))
    }

    fun logout() {
        clearUserBaseInfo()
//        cookieJar.clear()
        accountStatusFlow.tryEmit(AccountState.LogOut)
    }

    fun isLogin() = accountStatusFlow.value.isLogin
}