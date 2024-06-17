package com.carlostorres.apphabits.di

import android.content.Context
import android.content.SharedPreferences
import com.carlostorres.apphabits.onboarding.data.repository.OnboardingRepoImpl
import com.carlostorres.apphabits.onboarding.domain.repository.OnboardingRepo
import com.carlostorres.apphabits.onboarding.domain.usecases.CompleteOnboardingUseCase
import com.carlostorres.apphabits.onboarding.domain.usecases.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext con: Context
    ): SharedPreferences{
        
        return con.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)
        
    }
    @Provides
    @Singleton
    fun provideOnboardingRepo(
        sharedPreferences: SharedPreferences
    ): OnboardingRepo{

        return OnboardingRepoImpl(sharedPreferences)

    }

    @Provides
    @Singleton
    fun provideHasSennOnboardingUseCase(
        repo: OnboardingRepo
    ): HasSeenOnboardingUseCase{

        return HasSeenOnboardingUseCase(repo)

    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(
        repo: OnboardingRepo
    ): CompleteOnboardingUseCase = CompleteOnboardingUseCase(repo)
    
}