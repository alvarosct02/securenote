package com.asct94.securenote.presentation.notes.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.databinding.FragmentNoteAddEditBinding
import com.asct94.securenote.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddEditFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteAddEditBinding
    private val viewModel by viewModels<NoteAddEditViewModel>()

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
    }

    override fun setupObservers() {
        viewModel.event.collectWhenStarted { it ->
            when (it) {
                NoteAddEditEvent.OnSaveSuccess -> {
                    findNavController().popBackStack()
                }

                is NoteAddEditEvent.ShowError -> showError(it.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}