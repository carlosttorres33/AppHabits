package com.carlostorres.apphabits.authentication.domain.usecases

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    @Test
    fun `given low char password, return invalid password`() {

        val validatePasswordUseCase = ValidatePasswordUseCase()
        val input = "asd"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.Invalid("Password must be at least 8 characters long"),
            result
        )
    }

    @Test
    fun `given no lowercase password, return invalid password`() {

        val validatePasswordUseCase = ValidatePasswordUseCase()
        val input = "ASDASD123"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.Invalid("Password must contain at least one lowercase letter"),
            result
        )
    }
}