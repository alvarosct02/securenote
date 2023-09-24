package com.asct94.securenote.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.asct94.securenote.data.local.room.models.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id LIKE :id LIMIT 1")
    suspend fun getById(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Insert
    suspend fun insertAll(vararg users: NoteEntity)

    @Delete
    suspend fun delete(user: NoteEntity)

    @Transaction
    suspend fun deleteById(noteId: Int) {
        delete(getById(noteId))
    }
}