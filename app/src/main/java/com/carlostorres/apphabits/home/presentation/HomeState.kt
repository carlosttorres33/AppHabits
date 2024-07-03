package com.carlostorres.apphabits.home.presentation

import com.carlostorres.apphabits.home.domain.model.Habit
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

data class HomeState(
    val currentDate : ZonedDateTime = ZonedDateTime.now(),
    val selectedDate : ZonedDateTime = ZonedDateTime.now(),
    val habits : List<Habit> = mockHabtis
)

private val mockHabtis = (1 .. 30).map {

    val dates = mutableListOf<LocalDate>()
    if (it % 2 == 0){
        dates.add(LocalDate.now())
    }

    Habit(
        id = it.toString(),
        title = "Habito $it",
        frequency = emptyList(),
        completedDates = dates,
        reminder = LocalTime.now(),
        startDate = ZonedDateTime.now()
    )
}