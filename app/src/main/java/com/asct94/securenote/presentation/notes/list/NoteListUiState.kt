package com.asct94.securenote.presentation.notes.list

import com.asct94.securenote.domain.models.Note

data class NoteListUiState(
    val notes: List<Note> = emptyList()
)