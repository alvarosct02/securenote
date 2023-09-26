package com.asct94.securenote.presentation.settings

sealed interface SettingsEvent {
    data object JsonLoadProcessing : SettingsEvent
    data object JsonLoadSucceed : SettingsEvent
    data class JsonLoadError(val message: String) : SettingsEvent
}