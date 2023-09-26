package com.asct94.securenote.data.local.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteEntity")
class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val message: String,
    val color: Int,
    val updatedAt: Long,
)