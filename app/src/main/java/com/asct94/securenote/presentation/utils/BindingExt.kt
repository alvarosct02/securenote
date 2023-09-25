package com.asct94.securenote.presentation.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isVisible")
fun View.isVisibleBinder(visible: Boolean) {
    this.isVisible = visible
}