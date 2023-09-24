package com.asct94.securenote.features.notes.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asct94.securenote.databinding.FragmentNoteEditBinding
import com.asct94.securenote.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce

@AndroidEntryPoint
class NoteEditFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteEditBinding
    private val viewModel by viewModels<NoteEditViewModel>()
    private val args: NoteEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSetupCompleted() {
        super.onSetupCompleted()
        viewModel.fetchNote(args.noteId)
    }

    override fun setupViews() {

        binding.etTitle.addTextChangedListener { viewModel.updateTitle(it.toString()) }
        binding.etMessage.addTextChangedListener { viewModel.updateMessage(it.toString()) }
        binding.btSave.setOnClickListener { viewModel.saveNote() }
    }

    override fun setupObservers() {

        viewModel.uiState.debounce(400).collectWhenStarted {
            binding.etTitle.setTextKeepState(it.title)
            binding.etMessage.setTextKeepState(it.message)
        }

        viewModel.event.collectWhenStarted { it ->
            when (it) {
                NoteEditEvent.OnSaveSuccess -> {
                    findNavController().popBackStack()
                }

                is NoteEditEvent.ShowError -> showError(it.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}