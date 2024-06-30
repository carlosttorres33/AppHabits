package com.carlostorres.apphabits.authentication.domain.usecases

class SignUpUseCases (
    val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
