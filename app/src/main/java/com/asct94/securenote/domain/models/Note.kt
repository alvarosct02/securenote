package com.asct94.securenote.domain.models

import android.graphics.Color

data class Note(
    val id: Int = -1,
    val title: String,
    val message: String,
    val color: Int,
    val updateAt: Long,
) {
    companion object {
        val selectableColors = listOf(
            Color.parseColor("#ffab91"),
            Color.parseColor("#f48fb1"),
            Color.parseColor("#81deea"),
            Color.parseColor("#cf94da"),
            Color.parseColor("#e7ed9b"),
        )
    }
}