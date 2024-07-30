package com.habitsapp.authentication.domain.usecase

import com.habitsapp.authentication.domain.repository.AuthenticationRepository

class LogoutUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() {
        repository.logout()
    }
}