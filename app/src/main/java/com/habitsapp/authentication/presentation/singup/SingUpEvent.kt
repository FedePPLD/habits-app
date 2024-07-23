package com.habitsapp.authentication.presentation.singup

sealed interface SingUpEvent {
    data class EmailChange(val email: String) : SingUpEvent
    data class PasswordChange(val password: String) : SingUpEvent
    data object LogIn : SingUpEvent
    data object SignUp : SingUpEvent
}