package com.carlostorres.apphabits.authentication.domain.usecases

import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo

class LoginWithEmailUseCase(
    private val repo: AuthenticationRepo
) {

    suspend operator fun invoke(email: String, password: String) : Result<Unit>{
        return repo.login(email, password)
    }

}