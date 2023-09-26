package com.asct94.securenote.presentation.settings

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.BiometricsRepository
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.domain.repositories.SettingsRepository
import com.asct94.securenote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    biometricsRepository: BiometricsRepository,
    private val notesRepository: NotesRepository,
) : BaseViewModel<SettingsEvent>() {

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

    fun loadNotesFromJson(reader: InputStreamReader) = viewModelScope.launch(Dispatchers.IO) {
        var jsonText = ""
        _event.emit(SettingsEvent.JsonLoadProcessing)
        delay(1000)
        try {
            val br = BufferedReader(reader)
            var line: String?
            while (br.readLine().also { line = it } != null) {
                jsonText += line
            }
            br.close()

        } catch (e: IOException) {
            e.printStackTrace()
            _event.emit(SettingsEvent.JsonLoadError(e.message.orEmpty()))
            return@launch
        }

        try {
            val rawNotes = Json.decodeFromString<List<RawNote>>(jsonText)
            val notes = rawNotes.map {
                Note(
                    title = it.title,
                    message = it.message,
                    color = Note.selectableColors.random(),
                    updateAt = System.currentTimeMillis(),
                )
            }
            notesRepository.saveMultipleNotes(notes)
            _event.emit(SettingsEvent.JsonLoadSucceed)
        } catch (e: Exception) {
            _event.emit(SettingsEvent.JsonLoadError(e.message.orEmpty()))

        }
    }

    @Serializable
    data class RawNote(val title: String, val message: String)
}
