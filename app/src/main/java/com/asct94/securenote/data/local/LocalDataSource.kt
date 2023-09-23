package com.asct94.securenote.data.local

import com.asct94.securenote.data.local.room.dao.NoteDao
import com.asct94.securenote.data.local.room.models.NoteEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LocalDataSource @Inject constructor(
    private val noteDao: NoteDao,
) {

    private suspend fun seedDbIfEmpty() {
        val notes = noteDao.getAll()
        if (notes.isNotEmpty()) return
        noteDao.insertAll(
            NoteEntity(
                title = "Title 1",
                message = "Vestibulum sit amet sapien a justo placerat volutpat at ac dolor. Duis dignissim ut leo ut volutpat. Proin nec ultricies ipsum, ac cursus metus. Aliquam vitae ornare elit. In hendrerit dui in facilisis volutpat."
            ), NoteEntity(
                title = "Title 2",
                message = "Sed iaculis, dui sit amet tempor placerat, urna turpis pulvinar ex, vel varius ipsum leo vel velit. Maecenas sem turpis, pellentesque a commodo ut, mattis sagittis mauris. "
            ), NoteEntity(
                title = "Title 3",
                message = "Sed pharetra erat vitae nisi laoreet suscipit. Donec eu nibh a orci sagittis vehicula. Quisque ultricies neque eu sem iaculis feugiat. Proin dictum aliquam dolor. In nec metus aliquet, mattis elit sed, semper mi."
            )
        )
    }

    suspend fun getNotes(): List<NoteEntity> = withContext(Dispatchers.IO) {
        delay(1000)
        seedDbIfEmpty()
        noteDao.getAll()
    }

    suspend fun getNote(id: Int): NoteEntity = withContext(Dispatchers.IO) {
        noteDao.findById(id)
    }

    fun isBiometricAppEnable(): Boolean {
        return true
    }
}