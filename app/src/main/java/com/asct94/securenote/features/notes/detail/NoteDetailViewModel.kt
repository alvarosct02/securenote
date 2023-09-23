package com.asct94.securenote.features.notes.detail

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteDetailUiState, Unit>(NoteDetailUiState.Init) {

    fun fetchNote(id: Int) = viewModelScope.launch {
        _uiState.value = NoteDetailUiState.Loading
        val note = notesRepository.getNote(id)
        _uiState.value = NoteDetailUiState.Success(note)
    }
}

sealed interface NoteDetailUiState {
    data object Init : NoteDetailUiState
    data object Loading : NoteDetailUiState
    data class Success(val content: Note) : NoteDetailUiState
}