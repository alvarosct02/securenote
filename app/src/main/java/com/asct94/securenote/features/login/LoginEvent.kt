package com.asct94.securenote.features.login

sealed interface LoginEvent {
    data object NavigateToApp : LoginEvent
    data class ShowError(val message: String) : LoginEvent
}