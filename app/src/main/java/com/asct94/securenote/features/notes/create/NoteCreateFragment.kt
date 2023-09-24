package com.asct94.securenote.features.notes.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asct94.securenote.databinding.FragmentNoteCreateBinding
import com.asct94.securenote.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce

@AndroidEntryPoint
class NoteCreateFragment : BaseFragment() {

    private lateinit var binding: FragmentNoteCreateBinding
    private val viewModel by viewModels<NoteCreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {

        binding.etTitle.addTextChangedListener { viewModel.updateTitle(it.toString()) }
        binding.etMessage.addTextChangedListener { viewModel.updateMessage(it.toString()) }
        binding.btSave.setOnClickListener { viewModel.saveNote() }
    }

    override fun setupObservers() {

        viewModel.uiState.debounce(400).collectWhenStarted {
            binding.etTitle.setTextKeepState(it.titleEdit)
            binding.etMessage.setTextKeepState(it.messageEdit)
        }

        viewModel.event.collectWhenStarted { it ->
            when (it) {
                NoteCreateEvent.OnSaveSuccess -> {
                    findNavController().popBackStack()
                }

                is NoteCreateEvent.ShowError -> showError(it.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}