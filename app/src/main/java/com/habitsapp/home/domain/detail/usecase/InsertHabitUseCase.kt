package com.habitsapp.home.domain.detail.usecase

import com.habitsapp.home.domain.models.Habit
import com.habitsapp.home.domain.repository.HomeRepository

class InsertHabitUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(habit: Habit) {
        repository.insertOrUpdateHabit(habit)
    }
}