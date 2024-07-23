package com.habitsapp.authentication.presentation.singup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.habitsapp.authentication.presentation.singup.SingUpEvent
import com.habitsapp.authentication.presentation.singup.SingUpState
import com.habitsapp.core.presentation.HabitPasswordTextfield
import com.habitsapp.core.presentation.HabitTextfield
import com.habitsapp.core.presentation.HabitTitle
import com.habitsapp.core.presentation.HabitsButton

@Composable
fun SingUpForm(
    state: SingUpState,
    onEvent: (SingUpEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HabitTitle(title = "create your account")
        Spacer(modifier = Modifier.height(32.dp))
        HabitTextfield(
            value = state.email,
            onValueChange = { onEvent(SingUpEvent.EmailChange(it)) },
            placeholder = "Email",
            contentDescription = "Enter email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            errorMessage = state.emailError,
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )
        HabitPasswordTextfield(
            value = state.password,
            onValueChange = { onEvent(SingUpEvent.PasswordChange(it)) },
            placeholder = "Password",
            contentDescription = "Enter password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.clearFocus()
                onEvent(SingUpEvent.SignUp)
            }),
            errorMessage = state.passwordError,
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        HabitsButton(
            text = "Create account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SingUpEvent.SignUp)
        }
        TextButton(onClick = { onEvent(SingUpEvent.LogIn) }) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign in")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}