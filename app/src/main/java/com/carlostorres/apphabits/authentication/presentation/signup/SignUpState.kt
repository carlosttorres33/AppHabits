package com.carlostorres.apphabits.authentication.presentation.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isSignedIn: Boolean = false,
    val isLoading : Boolean = false,
    val logIn : Boolean = false
)
