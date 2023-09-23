package com.asct94.securenote.features.notes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<NoteListUiState>(NoteListUiState.Init)
    val uiState: LiveData<NoteListUiState> = _uiState

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