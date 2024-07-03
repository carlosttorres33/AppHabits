package com.carlostorres.apphabits.home.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

data class Habit(
    val id : String,
    val title : String,
    val frequency : List<DayOfWeek>,
    val completedDates : List<LocalDate>,
    val reminder : LocalTime,
    val startDate : ZonedDateTime
)
