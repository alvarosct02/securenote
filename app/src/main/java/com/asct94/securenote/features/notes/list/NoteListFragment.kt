package com.asct94.securenote.features.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.databinding.FragmentNoteListBinding
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

    override fun setupViews() {
        adapter = NotesAdapter {
            val action =
                NoteListFragmentDirections.actionNoteListFragmentToNoteEditFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvNotes.adapter = adapter
        binding.btCreate.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteAddFragment()
            findNavController().navigate(action)
        }
    }

    override fun setupObservers() {
        viewModel.uiState.collectWhenStarted {
            adapter.submitList(it.notes)
        }
    }
}