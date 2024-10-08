package com.carlostorres.apphabits.authentication.domain.repository

interface AuthenticationRepo {

    suspend fun login (email: String, password: String): Result<Unit>
    suspend fun signUp (email: String, password: String): Result<Unit>
    fun getUserId() : String?
    suspend fun logout()

}