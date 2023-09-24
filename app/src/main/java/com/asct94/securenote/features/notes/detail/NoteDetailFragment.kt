package com.asct94.securenote.features.notes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asct94.securenote.R
import com.asct94.securenote.databinding.FragmentNoteDetailBinding
import com.asct94.securenote.domain.models.Note
import com.asct94.securenote.features.base.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private val viewModel by viewModels<NoteDetailViewModel>()
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSetupCompleted() {
        super.onSetupCompleted()
        viewModel.fetchNote(args.noteId)
    }

    override fun setupViews() {
        binding.btEdit.setOnClickListener {
            val action =
                NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteEditFragment(args.noteId)
            findNavController().navigate(action)
        }
        binding.btDelete.setOnClickListener {
            openDeleteConfirmationDialog()
        }
    }

    override fun setupObservers() {
        viewModel.uiState.collectWhenStarted {
            when (it) {
                NoteDetailUiState.Init -> onInit()
                NoteDetailUiState.Loading -> onLoading()
                is NoteDetailUiState.Success -> onSuccess(it.content)
            }
        }
        viewModel.event.collectWhenStarted {
            when (it) {
                NoteDetailEvent.OnNoteDeleted -> {
                    findNavController().popBackStack()
                }

                else -> Unit
            }
        }
    }

    private fun openDeleteConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.note_delete_dialog_title))
            .setMessage(resources.getString(R.string.note_delete_dialog_message))
            .setPositiveButton(resources.getString(R.string.delete)) { dialog, which ->
                viewModel.deleteNote(args.noteId)
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun onSuccess(note: Note) {
        binding.tvTitle.text = note.title
        binding.tvMessage.text = note.message
    }

    private fun onLoading() {
//        Show Loaders
    }

    private fun onInit() {
//        Show Shimmers
    }
}