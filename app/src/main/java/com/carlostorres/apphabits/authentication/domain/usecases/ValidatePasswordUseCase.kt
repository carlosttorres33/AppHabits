package com.carlostorres.apphabits.authentication.domain.usecases

class ValidatePasswordUseCase {

    operator fun invoke(password: String): PasswordResult {

        if (password.length < 8){
            return PasswordResult.Invalid("Password must be at least 8 characters long")
        } else if (!password.any { it.isDigit() }){
            return PasswordResult.Invalid("Password must contain at least one digit")
        } else if (!password.any { it.isUpperCase() }){
            return PasswordResult.Invalid("Password must contain at least one uppercase letter")
        } else if (!password.any { it.isLowerCase() }){
            return PasswordResult.Invalid("Password must contain at least one lowercase letter")
        }

        return PasswordResult.Valid

    }

}

sealed class PasswordResult{
    object Valid: PasswordResult()
    data class Invalid(val message: String): PasswordResult()
}