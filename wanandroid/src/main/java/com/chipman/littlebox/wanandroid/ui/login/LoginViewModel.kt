package com.chipman.littlebox.wanandroid.ui.login

import com.chipman.common.base.BaseViewModel
import com.chipman.data.di.WanAccountRepository
import com.chipman.domain.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LoginData(
    val phoneNum: String = "",
    val code: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    @WanAccountRepository val wanRepository: AccountRepository
) : BaseViewModel() {

//    private var _sendCodeStatuesFlow: MutableSharedFlow<State<String>> = MutableSharedFlow()
//    val sendCodeStatuesFlow = _sendCodeStatuesFlow.asSharedFlow()
//
//    private var _isEnableLiveData: MutableLiveData<Boolean> = MutableLiveData()
//    val isEnableLiveData: LiveData<Boolean> = _isEnableLiveData
//
//    private var _loginStateFlow: MutableSharedFlow<State<UserInfo>> = MutableSharedFlow()
//    val loginStateFlow: SharedFlow<State<UserInfo>> = _loginStateFlow.asSharedFlow()
//
//    private var _showDialogLiveData: MutableLiveData<Boolean> = MutableLiveData()
//    val showDialogLiveData: LiveData<Boolean> = _showDialogLiveData
//
//    var isAgree: Boolean = false // 是否勾选用户协议
//    var isSent: Boolean = false // 是否获取过验证码
//    private var _loginData: LoginData = LoginData() // 登录信息
//    private var fetchJob: Job? = null
//    private var suspendAction: (() -> Unit)? = null
//
//    private suspend fun checkRequirement(onlyAgree: Boolean = false, action: () -> Unit): Boolean {
//        if (!onlyAgree) {
//            if (!isSent) {
//                _sendCodeStatuesFlow.emit(State.Error("请先获取验证码"))
//                return false
//            }
//        }
//        if (!isAgree) {
//            this.suspendAction = action
//            _showDialogLiveData.postValue(true)
//            return false
//        }
//        return true
//    }
//
//    fun onInputChange(phoneValue: String, codeValue: String) {
//        _loginData = LoginData(
//            phoneNum = phoneValue,
//            code = codeValue
//        )
//        _isEnableLiveData.value = phoneValue.length == 11 && codeValue.length == 6
//    }
//
//    fun loginByCode() {
//        viewModelScope.launch {
//            if (checkRequirement(action = this@LoginViewModel::loginByCode)) {
//                loginRepository.logInByCode(_loginData.phoneNum, _loginData.code).map {
//                    State.fromResource(it)
//                }
//                    .onStart { _loginStateFlow.emit(State.loading()) }
//                    .collect {
//                        _loginStateFlow.emit(it)
//                    }
//            }
//        }
//    }
//
//    /**
//     * 微信登录授权
//     */
//    fun sendAuthRequest() {
//        viewModelScope.launch {
//            if (checkRequirement(true, action = this@LoginViewModel::sendAuthRequest)) {
//                if (!App.iwxapi.isWXAppInstalled) {
//                    ToastUtils.show("您还未安装微信客户端！")
//                    return@launch
//                }
//                val req = SendAuth.Req()
//                req.scope = LoginActivity.WX_SCOPE
//                req.state = LoginActivity.WX_LOGIN_STATE
//                App.iwxapi.sendReq(req)
//            }
//        }
//    }
//
//    /**
//     * 授权后，发起登录请求
//     * @param authCode String
//     */
//    fun loginByWechat(authCode: String) {
//        viewModelScope.launch {
//            loginRepository.logInByWechat(authCode).map {
//                State.fromResource(it)
//            }
//                .onStart { _loginStateFlow.emit(State.loading()) }
//                .collect {
//                    _loginStateFlow.emit(it)
//                }
//        }
//    }
//
//    fun sendPhoneCode() {
//        fetchJob?.cancel()
//        fetchJob = viewModelScope.launch {
//            if (_loginData.phoneNum.isEmpty()) {
//                return@launch _sendCodeStatuesFlow.emit(State.Error("请先输入手机号"))
//            }
//            isSent = true
//            loginRepository.sendPhoneCode(_loginData.phoneNum).map {
//                State.fromResource(it)
//            }
//                .onStart {
//                    _sendCodeStatuesFlow.emit(State.loading())
//                }
//                .collect {
//                    _sendCodeStatuesFlow.emit(it)
//                }
//        }
//    }
//
//    fun dialogAgreeClick(check: Boolean) {
//        isAgree = check
//        if (check) {
//            suspendAction?.invoke()
//        } else {
//            suspendAction = null
//        }
//    }
//
//    fun loggedIn(userInfo: UserInfo) {
//        accountManager.saveUser(userInfo)
//        AccountStoreManager.setIsLogin(true)
//    }
}