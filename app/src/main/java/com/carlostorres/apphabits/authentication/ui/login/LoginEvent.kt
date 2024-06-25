package com.carlostorres.apphabits.authentication.ui.login

sealed interface LoginEvent {

    data class onEmailChange(val email: String) : LoginEvent
    data class onPasswordChange(val password: String) : LoginEvent
    object onLogin : LoginEvent
    object onSignUp : LoginEvent
}