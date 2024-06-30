package com.carlostorres.apphabits.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.apphabits.authentication.domain.usecases.PasswordResult
import com.carlostorres.apphabits.authentication.domain.usecases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent(event : SignUpEvents){

        when(event){
            is SignUpEvents.onEmailChange -> state = state.copy(email = event.email)
            SignUpEvents.onLogin -> state = state.copy(logIn = true)
            is SignUpEvents.onPasswordChange -> state = state.copy(password = event.password)
            SignUpEvents.onSignUp -> {
                signUp()
            }
        }

    }

    private fun signUp(){

        state = state.copy(emailError = null, passwordError = null)

        if (!signUpUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(emailError = "Invalid Email")
        }
        val passwordResult = signUpUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(passwordError = passwordResult.message)
        }

        if (state.emailError == null && state.passwordError == null) {

            state = state.copy(isLoading = true)

            viewModelScope.launch {
                signUpUseCases.signUpWithEmailUseCase(state.email, state.password)
                    .onSuccess {
                        state = state.copy(isSignedIn = true)
                    }
                    .onFailure {
                        state = state.copy(emailError = it.message)
                    }
                state = state.copy(isLoading = false)
            }

        }

    }

}