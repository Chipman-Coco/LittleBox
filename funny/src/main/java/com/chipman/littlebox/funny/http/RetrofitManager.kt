package com.chipman.littlebox.funny.http

import com.chipman.littlebox.funny.BuildConfig
import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.http.converter.GsonConverterFactory
import com.chipman.littlebox.funny.util.GlobalUtil
import com.chipman.littlebox.funny.util.SpUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private const val BASE_URL = "http://tools.cretinzp.com/jokes/"
    private const val TIME_OUT_SECONDS = 10L
    private val serviceMap = ConcurrentHashMap<String, BaseService>()

    private val loggingInterceptor by lazy {
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
        HttpLoggingInterceptor().setLevel(level)
    }

    private val httpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
//        .cookieJar()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseService> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        return serviceMap.getOrPut(serviceClass.name) {
            Retrofit.Builder()
                .baseUrl(baseUrl ?: BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass)
        } as T
    }

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder().apply {
                header("project_token", BuildConfig.TOKEN)
                header("token", SpUtil.getString("token", "").orEmpty()) // 用户凭证信息
                header("uk", GlobalUtil.getDeviceSerial()) // 用户设备唯一标识
                header("channel", "cretin_open_api")
                // 版本号1.0.0，版本标识100，系统为Android 10  例如："1.0.0;100;10"
                header("app", "${GlobalUtil.appVersionName};${GlobalUtil.appVersionCode};${GlobalUtil.getSDKVersionName()}")
                // 设备品牌和设备型号 例如："HUAWEI;CDY-AN00"
                header("device", "${GlobalUtil.deviceBrand};${GlobalUtil.deviceModel}")
            }.build()
//            Timber.d("request header:\n${request.headers}")
            return chain.proceed(request)
        }
    }

    /*class BasicParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url
            val url = originalHttpUrl.newBuilder().apply {
                addQueryParameter("udid", GlobalUtil.getDeviceSerial())
                if (!originalHttpUrl.toString().contains("api/v5/index/tab/allRec")) {
                    addQueryParameter("vc", GlobalUtil.versionCode.toString())
                    addQueryParameter("vn", GlobalUtil.versionName)
                }
            }.build()
            val request = originalRequest.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }*/
}