package com.carlostorres.apphabits.authentication.domain.usecases

import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo

class GetUserIdUseCase(
    private val repo: AuthenticationRepo
) {
    operator fun invoke(): String?{
        return repo.getUserId()
    }
}