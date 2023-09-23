package com.asct94.securenote.domain.repositories

interface SettingsRepository {
    suspend fun isBiometricCheckEnable(): Boolean
}