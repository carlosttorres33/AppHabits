package com.carlostorres.apphabits.authentication.domain.usecases

import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo

class LogOutUseCase(
    private val repo: AuthenticationRepo
) {
    suspend operator fun invoke(){
        return repo.logout()
    }
}