package com.habitsapp.home.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.habitsapp.R
import com.habitsapp.home.data.extension.goAsync
import com.habitsapp.home.domain.alarm.AlarmHandler
import com.habitsapp.home.domain.models.Habit
import com.habitsapp.home.domain.repository.HomeRepository
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val HABIT_ID = "habit_id"
        private const val CHANNEL_ID = "habits_channel"
    }

    @Inject
    lateinit var repository: HomeRepository

    @Inject
    lateinit var alarmHandler: AlarmHandler

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        val id = intent.getStringExtra(HABIT_ID) ?: return@goAsync
        val habit = repository.getHabitById(id)
        createNotificationChannel(context)
        if (!habit.completedDates.contains(LocalDate.now())) {
            showNotification(context, habit)
        }
        alarmHandler.setRecurringAlarm(habit)
    }

    private fun showNotification(context: Context, habit: Habit) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(habit.name)
            .setSmallIcon(R.drawable.notification_icon)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(habit.id.hashCode(), notification)
    }

    private fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Habit Reminder Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Get your habits reminder!"
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}