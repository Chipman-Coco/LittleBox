package com.chipman.littlebox.funny.service.impl

import com.chipman.littlebox.funny.http.RetrofitManager
import com.chipman.littlebox.funny.service.WeatherService

class WeatherServiceImpl : WeatherService {

    private val service by lazy { RetrofitManager.getService(WeatherService::class.java) }

    override suspend fun getCurrentWeather(cityName: String) = service.getCurrentWeather(cityName)

    override suspend fun getForecastWeather(cityName: String) = service.getForecastWeather(cityName)
}