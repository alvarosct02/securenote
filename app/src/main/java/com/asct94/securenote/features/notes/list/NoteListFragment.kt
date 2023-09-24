package com.asct94.securenote.features.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.databinding.FragmentNoteListBinding
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel by viewModels<NoteListViewModel>()

    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSetupCompleted() {
        super.onSetupCompleted()
        viewModel.fetchNotes()
    }

    override fun setupViews() {
        adapter = NotesAdapter(emptyList()) {
            val action =
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvNotes.adapter = adapter
    }

    override fun setupObservers() {
        viewModel.uiState.collectWhenStarted {
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