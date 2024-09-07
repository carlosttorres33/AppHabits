package com.carlostorres.apphabits.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carlostorres.apphabits.home.data.local.entity.HabitEntity
import com.carlostorres.apphabits.home.data.local.entity.HabitSyncEntity
import com.carlostorres.apphabits.home.data.local.typeconverters.HabitTypeConverter

@Database (entities = [HabitEntity::class, HabitSyncEntity::class], version = 2, exportSchema = false)
@TypeConverters(
    HabitTypeConverter::class
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao() : HabitDao


}