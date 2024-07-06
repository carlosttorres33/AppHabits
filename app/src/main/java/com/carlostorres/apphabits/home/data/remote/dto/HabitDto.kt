package com.carlostorres.apphabits.home.data.remote.dto

import androidx.room.PrimaryKey

data class HabitDto(
    val title : String,
    val reminder : Long,
    val startDate : Long,
    val frequency : List<Int>,
    val completedDates : List<Long>?,
)
