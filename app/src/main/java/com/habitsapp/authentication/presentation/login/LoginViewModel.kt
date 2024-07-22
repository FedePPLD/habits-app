package com.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habitsapp.authentication.domain.usecase.LoginUseCases
import com.habitsapp.authentication.domain.usecase.PasswordResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            is LoginEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.Login -> {

                login()
            }

            is LoginEvent.SignUp -> {
                state = state.copy(singUp = true)
            }
        }
    }

    private fun login() {
        state = state.copy(emailError = null, passwordError = null)

        if (!loginUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(emailError = "The email is invalid")
        }

        val passwordResult = loginUseCases.validatePasswordUseCase(state.password)

        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(passwordError = passwordResult.errorMesagge)
        }

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )

            viewModelScope.launch {
                loginUseCases.loginUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        emailError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }
}