package com.asct94.securenote.data.local

import com.asct94.securenote.domain.models.Note
import javax.inject.Inject

class NoteEntityMapper @Inject constructor() {

    fun fromDomain(note: Note): NoteEntity = note.let {
        NoteEntity(
            title = it.title,
            message = it.message,
        )
    }

    fun toDomain(noteEntity: NoteEntity): Note = noteEntity.let {
        Note(
            title = it.title,
            message = it.message,
        )
    }
}