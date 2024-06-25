package com.carlostorres.apphabits.authentication.ui.login

data class LoginState(
    val email : String = "",
    val password : String = "",
    val emailError : String? = null,
    val passwordError : String? = null,
    val signUp: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
)
