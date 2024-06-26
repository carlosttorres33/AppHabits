package com.carlostorres.apphabits.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

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

        //state = state.copy(isSignedIn = true)
    }

}