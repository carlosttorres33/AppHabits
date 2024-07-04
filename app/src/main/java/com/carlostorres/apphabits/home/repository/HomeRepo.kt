package com.carlostorres.apphabits.home.repository

import com.carlostorres.apphabits.home.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HomeRepo {

    fun getAllHabitsForSelectedDate(date : ZonedDateTime) : Flow<List<Habit>>

    suspend fun upsertHabit(habit : Habit)
    suspend fun getHabitById(id : String): Habit

}