package com.asct94.securenote.data

import com.asct94.securenote.data.local.LocalDataSource
import com.asct94.securenote.domain.repositories.SettingsRepository
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) : SettingsRepository {

    override suspend fun isBiometricCheckEnable(): Boolean {
        return false
    }
}