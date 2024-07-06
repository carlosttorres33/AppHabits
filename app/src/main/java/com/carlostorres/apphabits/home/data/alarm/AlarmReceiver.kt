package com.carlostorres.apphabits.home.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.carlostorres.apphabits.R
import com.carlostorres.apphabits.home.data.extensions.goAsync
import com.carlostorres.apphabits.home.domain.alarm.AlarmHandler
import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val HABIT_ID = "HABIT_ID"
        const val CHANEL_ID = "habits_channel"
    }

    @Inject
    lateinit var repo: HomeRepo

    @Inject
    lateinit var alarmHandler: AlarmHandler

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) {
            return@goAsync
        }

        val id = intent.getStringExtra(HABIT_ID) ?: return@goAsync

        val habit = repo.getHabitById(id)

        createNotificationChannel(context)

        if (!habit.completedDates.contains(LocalDate.now())) {
            showNotification(habit, context)
        }

        alarmHandler.setRecurringAlarm(habit)

    }

    private fun showNotification(habit: Habit, context: Context) {

        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val notification = NotificationCompat.Builder(context, CHANEL_ID)
            .setContentTitle(habit.title)
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(habit.id.hashCode(), notification)

    }

    private fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANEL_ID,
                "Habit Reminder Channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "Get your habits reminder!"

            val notificationManager = context.getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)

        }

    }
}