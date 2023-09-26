package com.asct94.securenote.presentation.base

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

abstract class BiometricFragment : BaseFragment() {

    fun showBiometricDialog(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        val activity = activity ?: return
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    onFailure(errString.toString())
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password").build()

        biometricPrompt.authenticate(promptInfo)

    }
}