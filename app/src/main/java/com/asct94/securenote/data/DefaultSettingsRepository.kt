package com.asct94.securenote.data

import com.asct94.securenote.data.local.PreferencesApp
import com.asct94.securenote.domain.repositories.SettingsRepository
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
    private val preferencesApp: PreferencesApp
) : SettingsRepository {

    override suspend fun isBiometricAppEnable(): Boolean {
        return preferencesApp.appBiometrics
    }

    override suspend fun changeBiometricAppEnable(enable: Boolean) {
        preferencesApp.appBiometrics = enable
    }
}