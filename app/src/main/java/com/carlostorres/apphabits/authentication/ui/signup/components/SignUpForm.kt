package com.carlostorres.apphabits.authentication.ui.signup.components

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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.carlostorres.apphabits.authentication.presentation.signup.SignUpEvents
import com.carlostorres.apphabits.authentication.presentation.signup.SignUpState
import com.carlostorres.apphabits.core.ui.HabitPasswordTextfield
import com.carlostorres.apphabits.core.ui.HabitTextfield
import com.carlostorres.apphabits.core.ui.HabitButton
import com.carlostorres.apphabits.core.ui.HabitTitle

@Composable
fun SignUpForm(
    state : SignUpState,
    onEvent: (SignUpEvents) -> Unit,
    modifier : Modifier = Modifier
){

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        HabitTitle(title = "Create an account")

        Spacer(modifier = Modifier.height(32.dp))

        HabitTextfield(
            value = state.email,
            onValueChange = {
                onEvent(SignUpEvents.onEmailChange(it))
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
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )

        HabitPasswordTextfield(
            value = state.password,
            onValueChange = {
                onEvent(SignUpEvents.onPasswordChange(it))
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
                    onEvent(SignUpEvents.onSignUp)
                }
            ),
            backgroundColor = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        HabitButton(
            text = "Create Account",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SignUpEvents.onSignUp)
        }

        TextButton(
            onClick = {
                onEvent(SignUpEvents.onLogin)
            }
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account?")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" Sign ip")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}