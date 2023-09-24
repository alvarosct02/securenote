package com.asct94.securenote.data

import com.asct94.securenote.domain.repositories.SettingsRepository
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
) : SettingsRepository {

    override suspend fun isBiometricAppEnable(): Boolean {
        return true
    }
}