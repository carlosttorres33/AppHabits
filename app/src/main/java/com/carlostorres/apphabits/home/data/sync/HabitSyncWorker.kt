package com.carlostorres.apphabits.home.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.local.entity.HabitSyncEntity
import com.carlostorres.apphabits.home.data.mappers.toDomain
import com.carlostorres.apphabits.home.data.mappers.toDto
import com.carlostorres.apphabits.home.data.remote.HomeApi
import com.carlostorres.apphabits.home.data.remote.util.resultOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import okhttp3.internal.wait

@HiltWorker
class HabitSyncWorker @AssistedInject constructor (
    @Assisted
    val context: Context,
    @Assisted
    val workerParameters: WorkerParameters,
    private val api : HomeApi,
    private val dao : HabitDao
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 3){
            return Result.failure()
        }
        val items = dao.getAllHabitsSync()
//        items.forEach {
//            runSync(it.id)
//        }
        return try {
            supervisorScope {
                val jobs = items.map { habit ->
                    async {
                        runSync(habit)
                    }
                }
                jobs.awaitAll()
            }
            Result.success()
        }catch (e:Exception){
            Result.retry()
        }
    }

    private suspend fun runSync(entity: HabitSyncEntity) {
        val habit = dao.getHabitById(entity.id).toDomain().toDto()
        resultOf {
            api.insertHabit(habit)
        }.onSuccess {
            dao.deleteHabitSync(entity)
        }.onFailure {
            throw it
        }

    }

}