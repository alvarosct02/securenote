package com.asct94.securenote.features.notes.edit

import com.asct94.securenote.domain.models.Note

data class NoteEditUiState(
    private val originalNote: Note? = null,
    private val messageEdit: String? = null,
    private val titleEdit: String? = null,
    val isLoading: Boolean = false
) {
    val id: Int = originalNote?.id ?: -1
    val title: String = titleEdit ?: originalNote?.title.orEmpty()
    val message: String = messageEdit ?: originalNote?.message.orEmpty()
}