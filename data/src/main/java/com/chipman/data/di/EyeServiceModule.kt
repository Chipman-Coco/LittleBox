package com.chipman.data.di

import com.chipman.common.http.RetrofitManager
import com.chipman.data.service.eyepetizer.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EyeServiceModule {

    @Singleton
    @Provides
    fun provideHomeService(): HomeService {
        return RetrofitManager.create(HomeService::class.java)
    }

    @Singleton
    @Provides
    fun provideCommunityService(): CommunityService {
        return RetrofitManager.create(CommunityService::class.java)
    }

    @Singleton
    @Provides
    fun provideNotificationService(): NotificationService {
        return RetrofitManager.create(NotificationService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchService(): SearchService {
        return RetrofitManager.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideVideoService(): VideoService {
        return RetrofitManager.create(VideoService::class.java)
    }
}