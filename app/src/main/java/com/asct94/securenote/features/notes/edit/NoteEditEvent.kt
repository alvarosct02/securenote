package com.asct94.securenote.features.notes.edit

sealed interface NoteEditEvent {
    data object OnSaveSuccess : NoteEditEvent
    data class ShowError(val message: String) : NoteEditEvent
}