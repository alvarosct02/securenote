package com.asct94.securenote.data.local.room

import android.os.Build
import androidx.room.Database
import androidx.room.RoomDatabase
import com.asct94.securenote.data.local.room.dao.NoteDao
import com.asct94.securenote.data.local.room.models.NoteEntity
import java.security.SecureRandom

@Database(entities = [NoteEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

fun generateRandomKey(): ByteArray = ByteArray(32).apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        SecureRandom.getInstanceStrong().nextBytes(this)
    } else {
        SecureRandom().nextBytes(this)
    }
}