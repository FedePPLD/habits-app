package com.habitsapp.authentication.domain.usecase

data class LoginUseCases(
    val loginUseCase: LoginUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)