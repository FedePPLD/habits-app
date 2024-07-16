package com.habitsapp.navigation

sealed class NavigationRoute(val route: String) {
    object Onboarding: NavigationRoute("onboarding")
}