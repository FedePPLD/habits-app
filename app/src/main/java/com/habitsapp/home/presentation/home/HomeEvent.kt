package com.habitsapp.home.presentation.home

import java.time.ZonedDateTime

sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime): HomeEvent
    data class CompleteHabit(val habitId: String): HomeEvent
}