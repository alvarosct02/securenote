package com.asct94.securenote.features.login

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.repositories.AuthRepository
import com.asct94.securenote.domain.repositories.BiometricsRepository
import com.asct94.securenote.domain.repositories.SettingsRepository
import com.asct94.securenote.features.base.BaseViewModel2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val biometricsRepository: BiometricsRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel2<LoginEvent>() {

    val password = MutableStateFlow("")

    init {
        showBiometricPromptIfAble()
    }

    fun validatePassword() = viewModelScope.launch {
        val isValid = authRepository.validatePassword(password.value)
        if (isValid) {
            _event.emit(LoginEvent.NavigateToApp)
        } else {
//            SHOW ERROR
        }
    }

    private fun showBiometricPromptIfAble() = viewModelScope.launch {
        delay(200)
        val shouldShow =
            settingsRepository.isBiometricAppEnable() && biometricsRepository.isBiometricFeatureDeviceEnable()
        if (shouldShow) {
            _event.emit(LoginEvent.ShowBiometricPrompt)
        }
    }
}