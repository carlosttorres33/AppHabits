package com.carlostorres.apphabits.home.presentation.home

import com.carlostorres.apphabits.home.domain.model.Habit
import java.time.ZonedDateTime

data class HomeState(
    val currentDate : ZonedDateTime = ZonedDateTime.now(),
    val selectedDate : ZonedDateTime = ZonedDateTime.now(),
    val habits : List<Habit> = emptyList()
)