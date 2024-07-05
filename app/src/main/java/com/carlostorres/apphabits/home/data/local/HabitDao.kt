package com.carlostorres.apphabits.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlostorres.apphabits.home.data.local.entity.HabitEntity
import com.carlostorres.apphabits.home.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertHabit(habitEntity: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertHabits(habitEntities: List<HabitEntity>)

    @Query("SELECT * FROM HabitEntity WHERE id = :id")
    fun getHabitById(id : String): HabitEntity

    @Query("SELECT * FROM HabitEntity WHERE startDate <= :date")
    fun getAllHabitsForSelectedDate(date : Long): Flow<List<HabitEntity>>

}