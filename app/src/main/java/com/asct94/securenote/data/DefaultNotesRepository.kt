package com.asct94.securenote.data

import com.asct94.securenote.data.local.LocalDataSource
import com.asct94.securenote.data.local.NoteEntityMapper
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import javax.inject.Inject

class DefaultNotesRepository @Inject constructor(
    private val entityMapper: NoteEntityMapper,
    private val localDataSource: LocalDataSource,
) : NotesRepository {

    override suspend fun getNotes(): List<Note> {
        val notes = localDataSource.getNotes()
        return notes.map(entityMapper::toDomain)
    }

    override suspend fun getNote(id: Int): Note {
        val note = localDataSource.getNote(id)
        return entityMapper.toDomain(note)
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = entityMapper.fromDomain(note)
        localDataSource.saveNote(noteEntity)
    }

    override suspend fun deleteNote(noteId: Int) {
        localDataSource.deleteNote(noteId)
    }
}