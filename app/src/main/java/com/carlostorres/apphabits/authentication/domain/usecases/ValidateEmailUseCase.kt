package com.carlostorres.apphabits.authentication.domain.usecases

import com.carlostorres.apphabits.authentication.domain.matcher.EmailMatcher

class ValidateEmailUseCase(
    private val emailMatcher: EmailMatcher
) {

    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }

}