package com.habitsapp.authentication.presentation.singup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habitsapp.authentication.domain.usecase.PasswordResult
import com.habitsapp.authentication.domain.usecase.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val singUpUseCases: SignUpUseCases,
) : ViewModel() {
    var state by mutableStateOf(SingUpState())
        private set

    fun onEvent(event: SingUpEvent) {
        when (event) {
            is SingUpEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            is SingUpEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }

            is SingUpEvent.LogIn -> {
                state = state.copy(logIn = true)
            }

            is SingUpEvent.SignUp -> {
                singUp()
            }
        }
    }

    private fun singUp() {
        state = state.copy(emailError = null, passwordError = null)

        if (!singUpUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(emailError = "The email is invalid")
        }

        val passwordResult = singUpUseCases.validatePasswordUseCase(state.password)

        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(passwordError = passwordResult.errorMesagge)
        }

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )

            viewModelScope.launch {
                singUpUseCases.signUpUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isSignedIn = true
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