package com.carlostorres.apphabits.home.data.repository

import com.carlostorres.apphabits.home.data.extensions.toStartOfDSateTimestamp
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.mappers.toDomain
import com.carlostorres.apphabits.home.data.mappers.toDto
import com.carlostorres.apphabits.home.data.mappers.toEntity
import com.carlostorres.apphabits.home.data.remote.HomeApi
import com.carlostorres.apphabits.home.data.remote.util.resultOf
import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

class HomeRepoImpl(
    private val dao: HabitDao,
    private val api: HomeApi
) : HomeRepo {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        val localFlow =
            dao.getAllHabitsForSelectedDate(date.toStartOfDSateTimestamp()).map { habitsList ->
                habitsList.map { singleHabit ->
                    singleHabit.toDomain()
                }
            }

        val apiFlow = getHabitsFromApi()

        return localFlow.combine(apiFlow) { db, api ->
            db
        }

    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {

        return flow {
            resultOf {
                val habits = api.getAllHabits().toDomain()
                upsertHabits(habits)
            }
            emit(emptyList<Habit>())
        }.onStart {
            emit(emptyList())
        }

    }

    override suspend fun upsertHabit(habit: Habit) {
        dao.upsertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }.onFailure {

        }
    }

    suspend fun upsertHabits(habits: List<Habit>) {
        dao.upsertHabits(
            habitEntities = habits.map { singleHabit ->
                singleHabit.toEntity()
            }
        )
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }

}