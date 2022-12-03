package com.chipman.littlebox.funny.di

import com.chipman.littlebox.funny.service.*
import com.chipman.littlebox.funny.service.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ServiceImplModule {

    @Binds
    @Singleton
    abstract fun getHelperService(impl: HelperServiceImpl): HelperService

    @Binds
    @Singleton
    abstract fun getHomeService(impl: HomeServiceImpl): HomeService

    @Binds
    @Singleton
    abstract fun getJokesService(impl: JokesServiceImpl): JokesService

    @Binds
    @Singleton
    abstract fun getUserService(impl: UserServiceImpl): UserService

    @Binds
    @Singleton
    abstract fun getWeatherService(impl: WeatherServiceImpl): WeatherService
}