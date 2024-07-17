package com.habitsapp.onboarding.domain.repository

interface OnboardinRepository {
    fun hasSeenOnboarding(): Boolean
    fun completeOnboarding()
}