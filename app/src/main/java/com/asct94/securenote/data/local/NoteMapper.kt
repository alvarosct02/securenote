package com.asct94.securenote.data.local

import com.asct94.securenote.data.local.room.models.NoteEntity
import com.asct94.securenote.domain.models.Note
import javax.inject.Inject

class NoteEntityMapper @Inject constructor() {

    fun fromDomain(note: Note): NoteEntity = note.let {
        NoteEntity(
            id = it.id,
            title = it.title,
            message = it.message,
        )
    }

    fun toDomain(noteEntity: NoteEntity): Note = noteEntity.let {
        Note(
            id = it.id,
            title = it.title,
            message = it.message,
        )
    }
}