package com.carlostorres.apphabits.authentication.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo : AuthenticationRepo
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event : LoginEvent){

        when(event){
            is LoginEvent.onEmailChange -> state = state.copy(email = event.email)
            LoginEvent.onLogin -> login()
            is LoginEvent.onPasswordChange -> state = state.copy(password = event.password)
            LoginEvent.onSignUp -> state = state.copy(signUp = true)
        }

    }

    private fun login(){
        viewModelScope.launch {
            authRepo.login(state.email, state.password)
                .onSuccess {

                }
                .onFailure {

                }
        }
    }

}