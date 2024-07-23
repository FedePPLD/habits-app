package com.habitsapp.authentication.domain.usecase

data class SignUpUseCases(
    val signUpUseCase: SignUpUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)