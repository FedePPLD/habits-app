package com.habitsapp.home.domain.alarm

import com.habitsapp.home.domain.models.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}
