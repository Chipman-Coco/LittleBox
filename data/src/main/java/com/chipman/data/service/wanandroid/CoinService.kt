package com.chipman.data.service.wanandroid

import com.chipman.common.result.ApiResponse
import com.chipman.data.service.BaseService
import com.chipman.model.wanandroid.CoinHistory
import com.chipman.model.wanandroid.CoinInfo
import com.chipman.model.wanandroid.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService : BaseService {

    @GET("/lg/coin/list/{page}/json")
    suspend fun getMyCoinList(@Path("page") page: Int): ApiResponse<PageResponse<CoinHistory>>

    @GET("coin/rank/{page}/json")
    suspend fun getCoinRanking(@Path("page") page: Int): ApiResponse<PageResponse<CoinInfo>>

}