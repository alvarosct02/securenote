package com.asct94.securenote.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asct94.securenote.data.local.room.dao.NoteDao
import com.asct94.securenote.data.local.room.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}