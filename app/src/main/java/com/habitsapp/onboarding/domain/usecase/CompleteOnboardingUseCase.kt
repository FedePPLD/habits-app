package com.habitsapp.onboarding.domain.usecase

import com.habitsapp.onboarding.domain.repository.OnboardinRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardinRepository
) {
    operator fun invoke() {
        repository.completeOnboarding()
    }
}