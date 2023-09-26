package com.asct94.securenote.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<E>() : ViewModel() {

    protected val _event = MutableSharedFlow<E>(replay = 0)
    val event: SharedFlow<E> = _event.asSharedFlow()

}