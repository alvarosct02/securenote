package com.asct94.securenote.domain.models

data class Note(
    val id: Int = -1,
    val title: String,
    val message: String
)