package com.asct94.securenote.presentation.notes.add_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.presentation.base.BaseViewModel2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NoteAddEditViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel2<NoteAddEditEvent>() {

    val title = MutableStateFlow("")
    val message = MutableStateFlow("")
    val color = MutableStateFlow(Note.selectableColors.random())

    val colorList = Note.selectableColors

    private var currentNoteId: Int? = null

    init {
        val noteId = savedStateHandle.get<Int>("noteId")
        noteId.takeIf { it != -1 }?.let {
            fetchNote(it)
        }
    }

    private fun fetchNote(id: Int) = viewModelScope.launch {
        val note = notesRepository.getNote(id)
        currentNoteId = note.id
        title.value = note.title
        message.value = note.message
        color.value = note.color
    }

    fun saveNote() = viewModelScope.launch {
        notesRepository.saveNote(
            Note(
                id = currentNoteId ?: -1,
                title = title.value,
                message = message.value,
                color = color.value,
                updateAt = System.currentTimeMillis(),
            )
        )
        _event.emit(NoteAddEditEvent.OnSaveSuccess)
    }
}
