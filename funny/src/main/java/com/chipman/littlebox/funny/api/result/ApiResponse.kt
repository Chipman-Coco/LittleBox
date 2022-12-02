package com.chipman.littlebox.funny.api.result

import com.chipman.littlebox.funny.api.exception.ApiException


sealed class ApiResponse<out T : Any> {

    /**
     * 成功
     */
    data class Success<T : Any>(val data: T) : ApiResponse<T>()

    /**
     * 业务错误
     */
    data class BizError(
        val errorCode: Int = 0,
        val errorMsg: String = ""
    ) : ApiResponse<Nothing>()

    /**
     * 其它错误
     */
    data class UnknownError(val throwable: Throwable) : ApiResponse<Nothing>()
}

inline val ApiResponse<*>.isSuccess: Boolean
    get() {
        return this is ApiResponse.Success
    }

fun <T : Any> ApiResponse<T>.getOrNull(): T? =
    when (this) {
        is ApiResponse.Success -> data
        is ApiResponse.BizError -> null
        is ApiResponse.UnknownError -> null
    }

fun <T : Any> ApiResponse<T>.exceptionOrNull(): Throwable? =
    when (this) {
        is ApiResponse.BizError -> ApiException(errorCode, errorMsg)
        is ApiResponse.Success -> null
        is ApiResponse.UnknownError -> throwable
    }

fun <T : Any> ApiResponse<T>.getOrThrow(): T =
    when (this) {
        is ApiResponse.BizError -> throw ApiException(errorCode, errorMsg)
        is ApiResponse.Success -> data
        is ApiResponse.UnknownError -> throw throwable
    }

inline fun <T : Any> ApiResponse<T>.getOrElse(default: ApiResponse<T>.() -> T): T =
    when (this) {
        is ApiResponse.Success -> data
        else -> default(this)
    }

inline fun <T : Any> ApiResponse<T>.whenSuccess(block: (T) -> Unit) {
    (this as? ApiResponse.Success)?.data?.also(block)
}

inline fun <T : Any> ApiResponse<T>.guardSuccess(block: () -> Nothing): T {
    if (this !is ApiResponse.Success) {
        block()
    }
    return this.data
}

fun <T : Any> ApiResponse<T>.toResult(): Result<T> {
    return if (this is ApiResponse.Success) {
        Result.Success(data)
    } else {
        Result.Error(exceptionOrNull())
    }
}