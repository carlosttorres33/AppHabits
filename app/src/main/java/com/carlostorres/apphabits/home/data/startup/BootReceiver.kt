package com.carlostorres.apphabits.home.data.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.carlostorres.apphabits.home.data.extensions.goAsync
import com.carlostorres.apphabits.home.data.local.HabitDao
import com.carlostorres.apphabits.home.data.mappers.toDomain
import com.carlostorres.apphabits.home.domain.alarm.AlarmHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var alarmHandler: AlarmHandler

    @Inject
    lateinit var homeDao: HabitDao

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {

        if (context == null || intent == null) return@goAsync

        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return@goAsync

        val items = homeDao.getAllHabits()
        items.forEach { habit ->
            alarmHandler.setRecurringAlarm(
                habit.toDomain()
            )
        }

    }
}