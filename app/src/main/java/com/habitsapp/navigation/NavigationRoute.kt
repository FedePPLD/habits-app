package com.habitsapp.navigation

sealed class NavigationRoute(val route: String) {
    data object Onboarding : NavigationRoute("onboarding")
    data object Login : NavigationRoute("login")
    data object SingUp : NavigationRoute("singup")
    data object Home : NavigationRoute("home")
}
