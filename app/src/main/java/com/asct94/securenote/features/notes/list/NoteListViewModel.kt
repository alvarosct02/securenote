package com.asct94.securenote.features.notes.list

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteListUiState, Unit>(NoteListUiState.Init) {

    init {
        fetchNotes()
    }

    private fun fetchNotes() = viewModelScope.launch {
        _uiState.value = NoteListUiState.Loading
        val notes = notesRepository.getNotes()
        _uiState.value = NoteListUiState.Success(notes)
    }
}

sealed interface NoteListUiState {
    data object Init : NoteListUiState
    data object Loading : NoteListUiState
    data object Empty : NoteListUiState
    data class Success(val content: List<Note>) : NoteListUiState
}