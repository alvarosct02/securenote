package com.asct94.securenote.presentation.settings

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.repositories.BiometricsRepository
import com.asct94.securenote.domain.repositories.SettingsRepository
import com.asct94.securenote.presentation.base.BaseViewModel2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val biometricsRepository: BiometricsRepository,
) : BaseViewModel2<Unit>() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            showBiometrics = biometricsRepository.isBiometricFeatureDeviceEnable(),
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        viewModelScope.launch {
            val enable = settingsRepository.isBiometricAppEnable()
            _uiState.update { it.copy(biometricsEnable = enable) }
        }
    }

    fun changeBiometricsEnable(enable: Boolean) = viewModelScope.launch {
        settingsRepository.changeBiometricAppEnable(enable)
        _uiState.update { it.copy(biometricsEnable = enable) }
    }
}
