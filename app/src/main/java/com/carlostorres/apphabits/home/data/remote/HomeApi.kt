package com.carlostorres.apphabits.home.data.remote

import com.carlostorres.apphabits.home.data.remote.dto.HabitDto
import com.carlostorres.apphabits.home.data.remote.dto.HabitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface HomeApi {

    companion object {
        const val BASE_URL = "https://apphabits-285de-default-rtdb.firebaseio.com/"
    }

    @GET("habits.json")
    suspend fun getAllHabits() : HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Body habit: HabitResponse)

}