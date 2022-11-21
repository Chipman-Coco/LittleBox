package com.chipman.domain.account

import android.content.Context
import com.chipman.domain.helper.Activities
import com.chipman.domain.helper.intentTo
import com.chipman.model.wanandroid.User
import com.hjq.toast.ToastUtils

sealed interface AccountState {

    object LogOut : AccountState

    data class LogIn(
        val isFromCookie: Boolean,
        val user: User? = null
    ) : AccountState
}

inline val AccountState.isLogin: Boolean
    get() {
        return this is AccountState.LogIn
    }

inline fun AccountState.checkLogin(context: Context, action: (AccountState) -> Unit) {
    if (this.isLogin) {
        action.invoke(this)
    } else {
        ToastUtils.show("请先登录")
        context.startActivity(intentTo(Activities.Login))
    }
}