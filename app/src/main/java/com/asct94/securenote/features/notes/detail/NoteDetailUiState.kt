package com.asct94.securenote.features.notes.detail

import com.asct94.securenote.domain.models.Note

sealed interface NoteDetailUiState {
    data object Init : NoteDetailUiState
    data object Loading : NoteDetailUiState
    data class Success(val content: Note) : NoteDetailUiState
}