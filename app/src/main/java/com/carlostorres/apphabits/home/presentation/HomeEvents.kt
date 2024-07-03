package com.carlostorres.apphabits.home.presentation

import com.carlostorres.apphabits.home.domain.model.Habit
import java.time.ZonedDateTime

sealed interface HomeEvents {

    data class ChangeDate(val date : ZonedDateTime) : HomeEvents
    data class CompleteHabit(val habit : Habit) : HomeEvents

}