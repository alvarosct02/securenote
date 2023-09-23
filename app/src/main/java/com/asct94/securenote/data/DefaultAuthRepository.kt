package com.asct94.securenote.data

import com.asct94.securenote.data.local.LocalDataSource
import com.asct94.securenote.domain.repositories.AuthRepository
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) : AuthRepository {

    override suspend fun validatePassword(password: String): Boolean {
        return password.length > 4
    }
}