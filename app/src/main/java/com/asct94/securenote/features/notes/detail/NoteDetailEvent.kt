package com.asct94.securenote.features.notes.detail

sealed interface NoteDetailEvent {
    data object OnNoteDeleted : NoteDetailEvent
    data class ShowError(val message: String) : NoteDetailEvent
}