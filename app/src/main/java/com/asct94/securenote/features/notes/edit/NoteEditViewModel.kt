package com.asct94.securenote.features.notes.edit

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : BaseViewModel<NoteEditUiState, NoteEditEvent>(NoteEditUiState()) {

    fun fetchNote(id: Int) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val note = notesRepository.getNote(id)
        _uiState.update { it.copy(originalNote = note, isLoading = false) }
    }

    fun updateTitle(title: String) = viewModelScope.launch {
        _uiState.update { it.copy(titleEdit = title) }
    }

    fun updateMessage(message: String) = viewModelScope.launch {
        _uiState.update { it.copy(messageEdit = message) }
    }

    fun saveNote() = viewModelScope.launch {
        val newNote = Note(
            id = _uiState.value.id,
            title = _uiState.value.title,
            message = _uiState.value.message,
        )
        notesRepository.saveNote(newNote)
        _event.emit(NoteEditEvent.OnSaveSuccess)
    }
}

data class NoteEditUiState(
    private val originalNote: Note? = null,
    private val messageEdit: String? = null,
    private val titleEdit: String? = null,
    val isLoading: Boolean = false
) {
    val id: Int = originalNote?.id ?: -1
    val title: String = titleEdit ?: originalNote?.title.orEmpty()
    val message: String = messageEdit ?: originalNote?.message.orEmpty()
}