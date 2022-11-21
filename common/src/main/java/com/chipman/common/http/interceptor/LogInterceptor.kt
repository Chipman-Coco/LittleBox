package com.chipman.common.http.interceptor

import androidx.viewbinding.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

// 日志拦截器
val loggingInterceptor by lazy {
    val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
    HttpLoggingInterceptor().setLevel(level)
}