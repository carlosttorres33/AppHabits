package com.carlostorres.apphabits.home.domain.alarm

import com.carlostorres.apphabits.home.domain.model.Habit

interface AlarmHandler {

    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)

}