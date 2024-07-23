package com.habitsapp.authentication.presentation.singup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.habitsapp.R
import com.habitsapp.authentication.presentation.singup.components.SingUpForm

@Composable
fun SingUpScreen(
    onSignIn: () -> Unit,
    onLogin: () -> Unit,
    viewModel: SingUpViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) {
            onSignIn()
        }
    }

    LaunchedEffect(state.logIn) {
        if (state.logIn) {
            onLogin()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(painterResource(id = R.drawable.signup), contentDescription = "singup image")
        SingUpForm(state, onEvent = viewModel::onEvent, modifier = Modifier.fillMaxWidth())
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
