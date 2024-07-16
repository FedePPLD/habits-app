package com.habitsapp.onboarding.presentation

import androidx.compose.runtime.Composable
import com.habitsapp.R
import com.habitsapp.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPagerInformation(
            "Welcome to Habits Tracker",
            "We can help you to be a better version of yourself",
            R.drawable.onboarding1
        ),
        OnboardingPagerInformation(
            "Create new habit easily",
            "A journey of a thousand miles begins with a first step",
            R.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            "Keep track of your progress",
            "The only fight that is lost is the one that is abandoned.",
            R.drawable.onboarding3
        ),
        OnboardingPagerInformation(
            "Join a supportive community",
            "Perseverance is the basis of all actions.",
            R.drawable.onboarding4
        ),
    )

    OnboardingPager(pages = pages, onFinish = onFinish)
}
