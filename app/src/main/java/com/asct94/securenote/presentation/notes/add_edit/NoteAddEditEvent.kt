package com.asct94.securenote.presentation.notes.add_edit

import androidx.annotation.StringRes

sealed interface NoteAddEditEvent {
    data object OnNoteSaved : NoteAddEditEvent
    data object OnNoteDeleted : NoteAddEditEvent
    data class ShowError(@StringRes val messageRes: Int) : NoteAddEditEvent
}