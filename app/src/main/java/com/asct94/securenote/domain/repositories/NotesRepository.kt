package com.asct94.securenote.domain.repositories

import com.asct94.securenote.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: Int): Note
    suspend fun saveNote(note:Note)
    suspend fun deleteNote(noteId:Int)
}