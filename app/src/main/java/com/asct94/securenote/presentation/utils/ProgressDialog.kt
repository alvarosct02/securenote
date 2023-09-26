package com.asct94.securenote.presentation.utils

import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.asct94.securenote.databinding.DialogProgressBinding

object ProgressDialogHelper {

    fun AlertDialog.Builder.showProgressDialog(@StringRes messageRes: Int) =
        showProgressDialog(context.getString(messageRes))

    fun AlertDialog.Builder.showProgressDialog(message: String): AlertDialog {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DialogProgressBinding.inflate(layoutInflater, null, false)
        binding.tvMessage.text = message
        this.setView(binding.root)
            .setCancelable(false)
        val dialog = this.create()
        dialog.show()
        return dialog
    }
}