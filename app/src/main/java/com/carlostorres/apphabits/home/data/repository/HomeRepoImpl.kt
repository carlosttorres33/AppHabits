package com.carlostorres.apphabits.home.data.repository

import com.carlostorres.apphabits.home.data.extensions.toStartOfDSateTimestamp
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.local.entity.HabitEntity
import com.carlostorres.apphabits.home.data.mappers.toDomain
import com.carlostorres.apphabits.home.data.mappers.toEntity
import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

class HomeRepoImpl(
    private val dao: HabitDao
) : HomeRepo {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return dao.getAllHabitsForSelectedDate(date.toStartOfDSateTimestamp()).map { habitsList ->
            habitsList.map { singleHabit ->
                singleHabit.toDomain()
            }
        }
    }

    override suspend fun upsertHabit(habit: Habit) {
        dao.upsertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }

}