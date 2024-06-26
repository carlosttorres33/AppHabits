package com.carlostorres.apphabits.authentication.domain.usecases

data class LoginUseCases(
    val loginWithEmailUseCase : LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
