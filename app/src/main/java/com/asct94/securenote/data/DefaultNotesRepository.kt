package com.asct94.securenote.data

import com.asct94.securenote.data.local.NoteEntityMapper
import com.asct94.securenote.data.local.room.dao.NoteDao
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultNotesRepository @Inject constructor(
    private val entityMapper: NoteEntityMapper,
    private val noteDao: NoteDao,
) : NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAll().map { it.map(entityMapper::toDomain) }
    }

    override suspend fun getNote(id: Int): Note {
        val note = noteDao.getById(id)
        return entityMapper.toDomain(note)
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = entityMapper.fromDomain(note)
        noteDao.insert(noteEntity)
    }

    override suspend fun deleteNote(noteId: Int) {
        noteDao.deleteById(noteId)
    }
}