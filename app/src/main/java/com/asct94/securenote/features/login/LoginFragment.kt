package com.asct94.securenote.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.databinding.FragmentLoginBinding
import com.asct94.securenote.features.base.BiometricFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BiometricFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun setupViews() {
        binding.btConfirm.setOnClickListener {
            viewModel.validatePassword()
        }
    }

    override fun setupObservers() {
        viewModel.event.collectWhenStarted {
            when (it) {
                LoginEvent.NavigateToApp -> {
                    navigateToApp()
                }

                LoginEvent.ShowBiometricPrompt -> {
                    showBiometricDialog(
                        onSuccess = ::navigateToApp, onFailure = ::showError
                    )
                }

                is LoginEvent.ShowError -> showError(it.message)
            }
        }
    }

    private fun navigateToApp() {
        val action = LoginFragmentDirections.actionLoginFragmentToNoteListFragment()
        findNavController().navigate(action)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}