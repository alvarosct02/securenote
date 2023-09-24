package com.asct94.securenote.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asct94.securenote.data.local.room.models.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Insert
    suspend fun insertAll(vararg users: NoteEntity)

    @Delete
    suspend fun delete(user: NoteEntity)
}