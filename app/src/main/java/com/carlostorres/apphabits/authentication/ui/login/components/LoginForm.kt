package com.carlostorres.apphabits.authentication.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.carlostorres.apphabits.authentication.presentation.login.LoginEvent
import com.carlostorres.apphabits.authentication.presentation.login.LoginState
import com.carlostorres.apphabits.core.HabitPasswordTextfield
import com.carlostorres.apphabits.core.HabitTextfield
import com.carlostorres.apphabits.core.presentation.HabitButton

@Composable
fun LoginForm(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login whit email",
                modifier = modifier
                    .padding(top = 12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )

            Divider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.background
            )

            HabitTextfield(
                value = state.email,
                onValueChange = {
                    onEvent(LoginEvent.onEmailChange(it))
                },
                placeholder = "Email",
                contentDescription = "Enter Email",
                modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                leadingIcon = Icons.Outlined.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
                errorMessage = state.emailError,
                isEnabled = !state.isLoading
            )

            HabitPasswordTextfield(
                value = state.password,
                onValueChange = {
                    onEvent(LoginEvent.onPasswordChange(it))
                },
                contentDescription = "Enter Password",
                modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                errorMessage = state.passwordError,
                isEnabled = !state.isLoading,
                placeholder = "Password",
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        focusManager.clearFocus()
                        onEvent(LoginEvent.onLogin)
                    }
                )
            )

            HabitButton(
                text = "Login",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                isEnabled = !state.isLoading
            ) {
                onEvent(LoginEvent.onLogin)
            }

            TextButton(
                onClick = {
                    onEvent(LoginEvent.onSignUp)
                }
            ) {
                Text(
                    text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            TextButton(
                onClick = {
                    onEvent(LoginEvent.onSignUp)
                }
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account?")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" Sign Up")
                        }
                    },
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        if (state.isLoading){
            CircularProgressIndicator()
        }

    }
}
