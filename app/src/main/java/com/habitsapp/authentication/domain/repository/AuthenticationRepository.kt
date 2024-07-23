package com.habitsapp.authentication.domain.repository

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun singup(email: String, password: String): Result<Unit>
    fun getUserId(): String?
}