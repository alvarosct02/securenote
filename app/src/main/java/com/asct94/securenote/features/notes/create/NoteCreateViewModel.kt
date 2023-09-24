package com.asct94.securenote.features.notes.create

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NoteCreateViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : BaseViewModel<NoteEditUiState, NoteCreateEvent>(NoteEditUiState()) {

    fun updateTitle(title: String) = viewModelScope.launch {
        _uiState.update { it.copy(titleEdit = title) }
    }

    fun updateMessage(message: String) = viewModelScope.launch {
        _uiState.update { it.copy(messageEdit = message) }
    }

    fun saveNote() = viewModelScope.launch {
        val newNote = Note(
            title = _uiState.value.titleEdit,
            message = _uiState.value.messageEdit,
        )
        notesRepository.saveNote(newNote)
        _event.emit(NoteCreateEvent.OnSaveSuccess)
    }
}

data class NoteEditUiState(
    val messageEdit: String = "",
    val titleEdit: String = "",
    val isLoading: Boolean = false
)