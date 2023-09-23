package com.asct94.securenote.features.login

sealed interface LoginEvent {
    data object NavigateToApp : LoginEvent
    data object ShowBiometricPrompt : LoginEvent
    data class ShowError(val message: String) : LoginEvent
}