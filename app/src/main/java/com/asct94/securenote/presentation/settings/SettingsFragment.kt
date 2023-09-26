package com.asct94.securenote.presentation.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asct94.securenote.R
import com.asct94.securenote.databinding.FragmentSettingsBinding
import com.asct94.securenote.presentation.base.BaseFragment
import com.asct94.securenote.presentation.utils.ProgressDialogHelper.showProgressDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStreamReader


@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private var loadingDialog: AlertDialog? = null
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun setupViews() {
        binding.swBiometrics.setOnCheckedChangeListener { _, enable ->
            viewModel.changeBiometricsEnable(enable)
        }

        binding.btLoadJson.setOnClickListener {
            showFileChooser()
        }
    }

    override fun setupObservers() {
        viewModel.event.collectWhenStarted {
            when (it) {
                SettingsEvent.JsonLoadProcessing -> {
                    loadingDialog =
                        AlertDialog.Builder(requireContext()).showProgressDialog(R.string.loading)
                }

                SettingsEvent.JsonLoadSucceed -> {
                    loadingDialog?.dismiss()
                    Snackbar.make(
                        binding.root,
                        R.string.notes_imported_correctly,
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction(R.string.view_all) {
                            findNavController().popBackStack()
                        }
                        .show()
                }

                is SettingsEvent.JsonLoadError -> {
                    loadingDialog?.dismiss()
                    Snackbar.make(
                        binding.root,
                        R.string.notes_imported_error,
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction(R.string.retry) {
                            showFileChooser()
                        }
                        .show()
                }
            }
        }
    }

    private val FILE_SELECT_CODE = 0

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_SELECT_CODE) {
            // Get the Uri of the selected file
            val uri: Uri = data?.data ?: return
            readFromJsonFile(uri)
        }
    }

    private fun readFromJsonFile(uri: Uri) {
        val reader = InputStreamReader(activity?.contentResolver?.openInputStream(uri))
        viewModel.loadNotesFromJson(reader)
    }

    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/json";
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(
                Intent.createChooser(intent, getString(R.string.select_a_file_to_upload)),
                FILE_SELECT_CODE
            )
        } catch (ex: ActivityNotFoundException) {
            Snackbar.make(
                binding.root,
                getString(R.string.please_install_a_file_manager),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}