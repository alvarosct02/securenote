package com.asct94.securenote.data

import android.content.Context
import android.util.Log
import androidx.biometric.BiometricManager
import com.asct94.securenote.domain.repositories.BiometricsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultBiometricsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : BiometricsRepository {

    override fun isBiometricFeatureDeviceEnable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                return true
            }

            else -> false
        }
    }
}