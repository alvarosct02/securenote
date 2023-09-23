package com.asct94.securenote.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.databinding.FragmentLoginBinding
import com.asct94.securenote.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        binding.btConfirm.setOnClickListener {
            viewModel.validatePassword()
        }
        binding.etPassword.doOnTextChanged { text, start, before, count ->
            viewModel.updatePassword(text.toString())
        }
    }

    override fun setupObservers() {
        viewModel.uiState.debounce(400).collectWhenStarted {
            binding.etPassword.setTextKeepState(it.password)
        }

        viewModel.event.collectWhenStarted {
            when (it) {
                LoginEvent.NavigateToApp -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToNoteListFragment()
                    findNavController().navigate(action)
                }

                is LoginEvent.ShowError -> TODO()
            }
        }
    }


    private fun onLoading() {
//        Show Loaders
    }

    private fun onInit() {
//        Show Shimmers
    }
}