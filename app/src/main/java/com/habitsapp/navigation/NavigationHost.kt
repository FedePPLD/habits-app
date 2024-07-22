package com.habitsapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.habitsapp.authentication.presentation.login.LoginScreen
import com.habitsapp.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(navHostController: NavHostController, startDestination: NavigationRoute) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen(onFinish = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Login.route)
            })
        }

        composable(NavigationRoute.Login.route) {
            LoginScreen(
                onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Home.route)
                },
                onSingUp = {
                    navHostController.navigate(NavigationRoute.SingUp.route)
                }
            )
        }

        composable(NavigationRoute.SingUp.route) {
            Text(text = "SingUp")
        }

        composable(NavigationRoute.Home.route) {
            Text(text = "Home")
        }
    }
}
