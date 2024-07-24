package com.habitsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.habitsapp.authentication.presentation.login.LoginScreen
import com.habitsapp.authentication.presentation.singup.SingUpScreen
import com.habitsapp.home.presentation.home.HomeScreen
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
                onSignUp = {
                    navHostController.navigate(NavigationRoute.SingUp.route)
                }
            )
        }

        composable(NavigationRoute.SingUp.route) {
            SingUpScreen(
                onSignIn = {
                    navHostController.navigate(NavigationRoute.Login.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onLogin = {
                    navHostController.navigate(NavigationRoute.Home.route)
                }

            )
        }

        composable(NavigationRoute.Home.route) {
            HomeScreen()
        }
    }
}
