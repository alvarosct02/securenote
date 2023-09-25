package com.asct94.securenote.presentation.notes.list

import androidx.lifecycle.viewModelScope
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.presentation.base.BaseViewModel2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel2<Unit>() {

    private val _uiState = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState

    private var getNotesJob: Job? = null

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        getNotesJob?.cancel()
        getNotesJob = notesRepository.getNotes().onEach { notes ->
            _uiState.update { it.copy(notes = notes) }
        }.launchIn(viewModelScope)
    }
}
