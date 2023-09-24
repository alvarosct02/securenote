package com.asct94.securenote.features.notes.detail

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteDetailUiState, NoteDetailEvent>(NoteDetailUiState.Init) {

    fun fetchNote(id: Int) = viewModelScope.launch {
        _uiState.value = NoteDetailUiState.Loading
        val note = notesRepository.getNote(id)
        _uiState.value = NoteDetailUiState.Success(note)
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
        notesRepository.deleteNote(id)
        _event.emit(NoteDetailEvent.OnNoteDeleted)
    }
}