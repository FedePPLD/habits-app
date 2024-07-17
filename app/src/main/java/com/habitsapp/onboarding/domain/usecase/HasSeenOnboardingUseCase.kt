package com.habitsapp.onboarding.domain.usecase

import com.habitsapp.onboarding.domain.repository.OnboardinRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardinRepository
) {
    operator fun invoke(): Boolean {
        return repository.hasSeenOnboarding()
    }
}