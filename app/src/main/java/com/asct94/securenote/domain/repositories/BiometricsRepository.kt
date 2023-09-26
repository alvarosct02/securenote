package com.asct94.securenote.domain.repositories

interface BiometricsRepository {
    fun isBiometricFeatureDeviceEnable(): Boolean
}