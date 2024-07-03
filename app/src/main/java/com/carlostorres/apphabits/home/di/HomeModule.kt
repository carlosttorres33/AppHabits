package com.carlostorres.apphabits.home.di

import com.carlostorres.apphabits.home.data.repository.HomeRepoImpl
import com.carlostorres.apphabits.home.domain.home.usecase.CompleteHabitUseCase
import com.carlostorres.apphabits.home.domain.home.usecase.GetAllHabitsForDatesUseCase
import com.carlostorres.apphabits.home.domain.home.usecase.HomeUseCases
import com.carlostorres.apphabits.home.repository.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeRepo() : HomeRepo = HomeRepoImpl()

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repo : HomeRepo
    ) = HomeUseCases(
        completeHabitUseCase = CompleteHabitUseCase(repo),
        getAllHabitsForDatesUseCase = GetAllHabitsForDatesUseCase(repo)
    )

}