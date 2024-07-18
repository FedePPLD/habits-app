package com.habitsapp.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val singUp: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
)