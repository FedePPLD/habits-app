package com.habitsapp.home.data.maper

import com.habitsapp.home.data.extension.toStartOfDateTimestamp
import com.habitsapp.home.data.extension.toTimestamp
import com.habitsapp.home.data.extension.toZoneDateTime
import com.habitsapp.home.data.extension.toZonedDateTime
import com.habitsapp.home.data.extension.toZonedTime
import com.habitsapp.home.data.local.entity.HabitEntity
import com.habitsapp.home.domain.models.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { DayOfWeek.of(it) },
        completedDates = this.completedDates.map { it.toZoneDateTime().toLocalDate() },
        reminder = this.reminder.toZoneDateTime().toLocalTime(),
        startDate = this.startDate.toZoneDateTime()
    )
}

fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZonedDateTime().toTimestamp() },
        reminder = this.reminder.toZonedTime().toTimestamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
}