package com.carlostorres.apphabits.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

@Entity
data class HabitEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val title : String,
    val frequency : List<Int>,
    val completedDates : List<Long>,
    val reminder : Long,
    val startDate : Long
)
