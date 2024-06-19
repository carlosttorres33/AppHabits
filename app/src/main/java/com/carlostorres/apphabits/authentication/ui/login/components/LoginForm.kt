package com.carlostorres.apphabits.authentication.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlostorres.apphabits.core.HabitPasswordTextfield
import com.carlostorres.apphabits.core.HabitTextfield
import com.carlostorres.apphabits.core.presentation.HabitButton

@Composable
fun LoginForm(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
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
            value = "Email",
            onValueChange = {

            },
            placeholder = "Email",
            contentDescription = "Enter Email",
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp).padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    //TODO
                }
            ),
            errorMessage = null,
            isEnabled = true
        )

        HabitPasswordTextfield(
            value = "Password",
            onValueChange = {

            },
            contentDescription = "Enter Password",
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp).padding(horizontal = 20.dp),
            errorMessage = null,
            isEnabled = true,
            placeholder = "Password",
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    //TODO
                }
            )
        )

        HabitButton(
            text = "Login",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            isEnabled = true
        ) {
            //
        }

        TextButton(
            onClick = {
                //
            }
        ) {
            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        TextButton(
            onClick = {
                //
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
}

@Preview
@Composable
fun LFP(){
    LoginForm()
}