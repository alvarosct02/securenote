package com.asct94.securenote.domain.repositories

import com.asct94.securenote.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getNotes(): List<Note>
}