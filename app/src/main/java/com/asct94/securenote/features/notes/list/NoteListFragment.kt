package com.asct94.securenote.features.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.asct94.securenote.databinding.FragmentNoteListBinding
import com.asct94.securenote.domain.models.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel by viewModels<NoteListViewModel>()
    private val adapter = NotesAdapter(emptyList())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotes.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                NoteListUiState.Empty -> onEmpty()
                NoteListUiState.Init -> onInit()
                NoteListUiState.Loading -> onLoading()
                is NoteListUiState.Success -> onSuccess(it.content)
            }
        }
    }

    private fun onSuccess(content: List<Note>) {
        adapter.updateList(content)
    }

    private fun onLoading() {
//        Show Loaders
    }

    private fun onInit() {
//        Show Shimmers
    }

    private fun onEmpty() {
//        Show EmptyScreen
    }
}