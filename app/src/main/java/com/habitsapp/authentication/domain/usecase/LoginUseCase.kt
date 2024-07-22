package com.habitsapp.authentication.domain.usecase

import com.habitsapp.authentication.domain.repository.AuthenticationRepository

class LoginUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }
}