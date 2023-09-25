package com.asct94.securenote.presentation.notes.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asct94.securenote.R
import com.asct94.securenote.databinding.FragmentNoteAddEditBinding
import com.asct94.securenote.domain.models.Note.Companion.NOTE_ID_NULL
import com.asct94.securenote.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddEditFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteAddEditBinding
    private val viewModel by viewModels<NoteAddEditViewModel>()
    private val args by navArgs<NoteAddEditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAddEditBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun setupViews() {
        binding.btSave.setOnClickListener { viewModel.saveNote() }
        binding.btDelete.setOnClickListener { viewModel.deleteNote() }
        val titleRes =
            R.string.title_edit_note.takeIf { args.noteId != NOTE_ID_NULL }
                ?: R.string.title_add_note
        binding.tvToolbarTitle.setText(titleRes)
    }

    override fun setupObservers() {
        viewModel.event.collectWhenStarted { it ->
            when (it) {
                NoteAddEditEvent.OnNoteSaved -> {
                    findNavController().popBackStack()
                }

                NoteAddEditEvent.OnNoteDeleted -> {
                    findNavController().popBackStack()
                }

                is NoteAddEditEvent.ShowError -> showError(it.messageRes)
            }
        }
    }

    private fun showError(messageRes: Int) {
        Snackbar.make(binding.root, getString(messageRes), Snackbar.LENGTH_SHORT).show()
//        Toast.makeText(requireContext(), getString(messageRes), Toast.LENGTH_SHORT).show()
    }
}