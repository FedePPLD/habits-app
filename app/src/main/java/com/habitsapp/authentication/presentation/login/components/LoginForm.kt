package com.habitsapp.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.habitsapp.core.presentation.HabitPasswordTextfield
import com.habitsapp.core.presentation.HabitTextfield
import com.habitsapp.core.presentation.HabitsButton

@Composable
fun LoginForm(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.background(Color.White, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Log in with Email",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.background,
        )
        HabitTextfield(
            value = "Email",
            onValueChange = {},
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
                //
            }),
            errorMessage = null,
            isEnabled = true
        )
        HabitPasswordTextfield(
            value = "Password",
            onValueChange = {},
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
                //
            }),
            errorMessage = null,
            isEnabled = true
        )
        HabitsButton(
            text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = true
        ) {
            //
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Forgot password?",
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign up")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}