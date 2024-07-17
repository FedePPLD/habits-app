package com.habitsapp.onboarding.data.repository

import android.content.SharedPreferences
import com.habitsapp.onboarding.domain.repository.OnboardinRepository

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : OnboardinRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }
}