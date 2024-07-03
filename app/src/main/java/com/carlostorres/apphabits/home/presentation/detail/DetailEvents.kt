package com.carlostorres.apphabits.home.presentation.detail

import java.time.DayOfWeek
import java.time.LocalTime

sealed interface DetailEvents {

    data class ReminderChanged(val time: LocalTime) : DetailEvents
    data class FrequencyChanged(val dayOfWeek: DayOfWeek) : DetailEvents
    data class TitleChanged(val title: String) : DetailEvents
    object HabitSaved : DetailEvents

}