package com.habitsapp.authentication.domain.usecase

import com.habitsapp.authentication.domain.repository.AuthenticationRepository

class SignUpUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signup(email, password)
    }
}