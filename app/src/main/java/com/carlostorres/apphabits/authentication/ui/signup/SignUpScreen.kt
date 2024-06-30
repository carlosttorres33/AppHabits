package com.carlostorres.apphabits.authentication.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.apphabits.R
import com.carlostorres.apphabits.authentication.presentation.signup.SignUpViewModel
import com.carlostorres.apphabits.authentication.ui.signup.components.SignUpForm

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignedIn: () -> Unit,
    onLogin: () -> Unit
) {

    val state = viewModel.state

    LaunchedEffect(state.isSignedIn){
        if (state.isSignedIn){
            onSignedIn()
        }
    }

    LaunchedEffect(state.logIn){
        if (state.logIn){
            onLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Image(painter = painterResource(R.drawable.signup), contentDescription = "")

        SignUpForm(state, viewModel::onEvent, modifier = Modifier.fillMaxWidth())

    }

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}