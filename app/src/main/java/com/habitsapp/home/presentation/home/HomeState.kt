package com.habitsapp.home.presentation.home

import com.habitsapp.home.domain.models.Habit
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = mockHabits,
)

private val mockHabits = (1..30).map {
    val dates = mutableListOf<LocalDate>()

    if (it % 2 == 0) {
        dates.add(LocalDate.now())
    }

    Habit(
        id = it.toString(),
        name = "Habit $it",
        frequency = emptyList(),
        completedDates = dates,
        reminder = LocalTime.now(),
        startDate = ZonedDateTime.now()
    )
}
