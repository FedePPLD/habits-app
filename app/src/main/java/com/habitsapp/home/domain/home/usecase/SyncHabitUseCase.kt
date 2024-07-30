package com.habitsapp.home.domain.home.usecase

import com.habitsapp.home.domain.repository.HomeRepository

class SyncHabitUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke() {
        repository.syncHabit()
    }
}