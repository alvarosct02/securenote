package com.asct94.securenote.domain.repositories

interface SettingsRepository {
    suspend fun isBiometricAppEnable(): Boolean
    suspend fun changeBiometricAppEnable(enable: Boolean)
}