package com.carlostorres.apphabits.home.di

import android.content.Context
import androidx.room.Room
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.local.HabitDatabase
import com.carlostorres.apphabits.home.data.local.typeconverters.HabitTypeConverter
import com.carlostorres.apphabits.home.data.remote.HomeApi
import com.carlostorres.apphabits.home.data.repository.HomeRepoImpl
import com.carlostorres.apphabits.home.domain.detail.usecases.DetailUseCases
import com.carlostorres.apphabits.home.domain.detail.usecases.GetHabitByIdUseCase
import com.carlostorres.apphabits.home.domain.detail.usecases.InsertHabitUseCase
import com.carlostorres.apphabits.home.domain.home.usecase.CompleteHabitUseCase
import com.carlostorres.apphabits.home.domain.home.usecase.GetAllHabitsForDatesUseCase
import com.carlostorres.apphabits.home.domain.home.usecase.HomeUseCases
import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideHabitDao(
        @ApplicationContext context: Context,
        moshi : Moshi
    ) : HabitDao = Room
        .databaseBuilder(
            context = context,
            klass = HabitDatabase::class.java,
            name = "habits_db"
        )
        .addTypeConverter(HabitTypeConverter(moshi))
        .build()
        .habitDao()

    @Singleton
    @Provides
    fun provideHomeRepo(
        dao: HabitDao,
        api : HomeApi
    ) : HomeRepo = HomeRepoImpl(dao, api)

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repo : HomeRepo
    ) = HomeUseCases(
        completeHabitUseCase = CompleteHabitUseCase(repo),
        getAllHabitsForDatesUseCase = GetAllHabitsForDatesUseCase(repo)
    )

    @Provides
    @Singleton
    fun provideDetailUseCases(
        repo : HomeRepo
    ) = DetailUseCases(
        insertHabitUseCase = InsertHabitUseCase(repo),
        getHabitByIdUseCase = GetHabitByIdUseCase(repo)
    )

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Singleton
    @Provides
    fun provideHomeApi(okHttpClient: OkHttpClient) : HomeApi{
        return Retrofit
            .Builder()
            .baseUrl(HomeApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(HomeApi::class.java)
    }

}