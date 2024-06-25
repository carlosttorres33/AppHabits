package com.carlostorres.apphabits.authentication.di

import com.carlostorres.apphabits.authentication.data.repository.AuthenticationRepoImpl
import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    
    @Provides
    @Singleton
    fun provideAuthRepo(): AuthenticationRepo = AuthenticationRepoImpl()
    
}