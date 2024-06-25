package com.carlostorres.apphabits.authentication.domain.repository

interface AuthenticationRepo {

    suspend fun login (email: String, password: String): Result<Unit>

}