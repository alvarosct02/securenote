package com.asct94.securenote.domain.models

import android.content.Context
import android.graphics.Color
import com.asct94.securenote.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int = NOTE_ID_NULL,
    val title: String,
    val message: String,
    val color: Int,
    val updateAt: Long,
) {

    fun updateAtString(context: Context): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = updateAt
        val format = context.getString(R.string.date_format)
        return SimpleDateFormat(format, Locale.getDefault())
            .format(calendar.time)
    }

    companion object {
        const val NOTE_ID_NULL = -1

        val selectableColors = listOf(
            Color.parseColor("#ffab91"),
            Color.parseColor("#f48fb1"),
            Color.parseColor("#81deea"),
            Color.parseColor("#cf94da"),
            Color.parseColor("#e7ed9b"),
        )
    }
}