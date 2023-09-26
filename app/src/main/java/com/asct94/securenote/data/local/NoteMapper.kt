package com.asct94.securenote.data.local

import com.asct94.securenote.data.local.room.models.NoteEntity
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.models.Note.Companion.NOTE_ID_NULL
import javax.inject.Inject

class NoteEntityMapper @Inject constructor() {

    fun fromDomain(note: Note): NoteEntity = note.let {
        NoteEntity(
            id = it.id.takeUnless { id -> id == NOTE_ID_NULL },
            title = it.title,
            message = it.message,
            color = it.color.takeIf { color -> color in Note.selectableColors }
                ?: Note.selectableColors.random(),
            updatedAt = it.updateAt,
        )
    }

    fun toDomain(noteEntity: NoteEntity): Note = noteEntity.let {
        Note(
            id = it.id ?: NOTE_ID_NULL,
            title = it.title,
            message = it.message,
            color = it.color,
            updateAt = it.updatedAt,
        )
    }
}