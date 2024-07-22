package com.habitsapp.authentication.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.habitsapp.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}