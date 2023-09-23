package com.asct94.securenote.features.login

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.repositories.AuthRepository
import com.asct94.securenote.domain.repositories.SettingsRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUiState, LoginEvent>(LoginUiState()) {

    init {
        showBiometricPromptIfAble()
    }

    fun updatePassword(password: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun validatePassword() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val password = _uiState.value.password
        val isValid = authRepository.validatePassword(password)
        if (isValid) {
            _event.emit(LoginEvent.NavigateToApp)
        } else {
//            SHOW ERROR
        }
        _uiState.update { it.copy(isLoading = false) }
    }

    fun showBiometricPromptIfAble() = viewModelScope.launch {
        val shouldShow = settingsRepository.isBiometricCheckEnable()
        if (shouldShow) {

        }
    }
}