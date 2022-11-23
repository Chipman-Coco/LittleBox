package com.chipman.domain.di

import com.chipman.data.di.WanAccountRepository
import com.chipman.domain.account.AccountRepository
import com.chipman.domain.account.WanAccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AccountModule {

    @WanAccountRepository
    @Binds
    abstract fun provideWanAccountRepository(wanAccountRepository: WanAccountRepositoryImpl): AccountRepository
}