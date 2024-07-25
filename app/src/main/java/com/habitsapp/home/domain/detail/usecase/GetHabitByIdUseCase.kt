package com.habitsapp.home.domain.detail.usecase

import com.habitsapp.home.domain.models.Habit
import com.habitsapp.home.domain.repository.HomeRepository

class GetHabitByIdUseCase(
  private val  repository: HomeRepository
) {
    suspend operator fun invoke(id:String): Habit {
        return repository.getHabitById(id)
    }
}