package com.chipman.littlebox.funny.service

import com.chipman.littlebox.funny.base.BaseService
import com.chipman.littlebox.funny.data.result.ApiResponse
import com.chipman.littlebox.funny.service.model.ForecastWeather
import com.chipman.littlebox.funny.service.model.TodayWeather
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService : BaseService {

    /**
     * 获取特定城市今日天气信息
     * @param cityName 例如：深圳市
     */
    @GET("weather/current/{city}")
    suspend fun getCurrentWeather(@Path("city") cityName: String): ApiResponse<TodayWeather>

    /**
     * 获取特定城市今天及未来天气信息
     */
    @GET("weather/forecast/{city}")
    suspend fun getForecastWeather(@Path("city") cityName: String): ApiResponse<ForecastWeather>
}