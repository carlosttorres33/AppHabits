package com.carlostorres.apphabits.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.apphabits.authentication.domain.usecases.LoginUseCases
import com.carlostorres.apphabits.authentication.domain.usecases.PasswordResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.onEmailChange -> state = state.copy(email = event.email)
            LoginEvent.onLogin -> login()
            is LoginEvent.onPasswordChange -> state = state.copy(password = event.password)
            LoginEvent.onSignUp -> state = state.copy(signUp = true)
        }

    }

    private fun login() {

        state = state.copy(emailError = null, passwordError = null)

        if (!loginUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(emailError = "Invalid Email")
        }
        val passwordResult = loginUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(passwordError = passwordResult.message)
        }

        if (state.emailError == null && state.passwordError == null) {

            state = state.copy(isLoading = true)

            viewModelScope.launch {
                loginUseCases.loginWithEmailUseCase(state.email, state.password)
                    .onSuccess {
                        state = state.copy(isLoggedIn = true)
                    }
                    .onFailure {
                        state = state.copy(emailError = it.message)
                    }
                state = state.copy(isLoading = false)
            }

        }
    }
}