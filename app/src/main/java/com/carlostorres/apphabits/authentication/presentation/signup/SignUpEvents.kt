package com.carlostorres.apphabits.authentication.presentation.signup

import com.carlostorres.apphabits.authentication.presentation.login.LoginEvent

sealed interface SignUpEvents {
    data class onEmailChange(val email: String) : SignUpEvents
    data class onPasswordChange(val password: String) : SignUpEvents
    object onLogin : SignUpEvents
    object onSignUp : SignUpEvents
}