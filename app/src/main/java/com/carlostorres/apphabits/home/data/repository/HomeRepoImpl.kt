package com.carlostorres.apphabits.home.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.carlostorres.apphabits.home.data.extensions.toStartOfDSateTimestamp
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.local.entity.HabitSyncEntity
import com.carlostorres.apphabits.home.data.mappers.toDomain
import com.carlostorres.apphabits.home.data.mappers.toDto
import com.carlostorres.apphabits.home.data.mappers.toEntity
import com.carlostorres.apphabits.home.data.mappers.toSyncEntity
import com.carlostorres.apphabits.home.data.remote.HomeApi
import com.carlostorres.apphabits.home.data.remote.util.resultOf
import com.carlostorres.apphabits.home.data.sync.HabitSyncWorker
import com.carlostorres.apphabits.home.domain.alarm.AlarmHandler
import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.Duration
import java.time.ZonedDateTime

class HomeRepoImpl(
    private val dao: HabitDao,
    private val api: HomeApi,
    private val alarmHandler: AlarmHandler,
    private val workManager: WorkManager
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

        handleAlarm(habit)

        dao.upsertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }.onFailure {
            dao.insertHabitSync(habit.toSyncEntity())
        }
    }

    private suspend fun upsertHabits(habits: List<Habit>) {
         habits.forEach{
             handleAlarm(it)
             dao.upsertHabit(it.toEntity())
         }
    }

    private suspend fun handleAlarm(habit: Habit){

        try {

            val previus = dao.getHabitById(habit.id)

            alarmHandler.cancel(previus.toDomain())

        }catch (e: Exception){
            //TODO: handle error
        }

        alarmHandler.setRecurringAlarm(habit)

    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun syncHabits() {
        val worker = OneTimeWorkRequestBuilder<HabitSyncWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
            .build()

        workManager.beginUniqueWork("sync_habit_id", ExistingWorkPolicy.REPLACE, worker).enqueue()
    }

}