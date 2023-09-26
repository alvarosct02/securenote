package com.asct94.securenote.data

import com.asct94.securenote.data.local.PreferencesApp
import com.asct94.securenote.domain.repositories.AuthRepository
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val preferencesApp: PreferencesApp
) : AuthRepository {

    override suspend fun validatePassword(password: String): Boolean {
        return preferencesApp.password?.let { password == it } ?: (password.length > 4)
    }
}