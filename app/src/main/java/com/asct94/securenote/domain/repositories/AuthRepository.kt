package com.asct94.securenote.domain.repositories

interface AuthRepository {
    suspend fun validatePassword(password: String): Boolean
}