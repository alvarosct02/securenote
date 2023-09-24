package com.asct94.securenote.features.notes.create

sealed interface NoteCreateEvent {
    data object OnSaveSuccess : NoteCreateEvent
    data class ShowError(val message: String) : NoteCreateEvent
}