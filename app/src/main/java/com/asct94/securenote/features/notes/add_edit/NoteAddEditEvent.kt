package com.asct94.securenote.features.notes.add_edit

sealed interface NoteAddEditEvent {
    data object OnSaveSuccess : NoteAddEditEvent
    data class ShowError(val message: String) : NoteAddEditEvent
}