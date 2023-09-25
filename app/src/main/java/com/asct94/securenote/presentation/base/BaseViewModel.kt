package com.asct94.securenote.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<T, E>(initialState: T) : ViewModel() {

    //    abstract val initialState: T
    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<T> = _uiState

    protected val _event = MutableSharedFlow<E>(replay = 0)
    val event: SharedFlow<E> = _event.asSharedFlow()

}

abstract class BaseViewModel2<E>() : ViewModel() {

    protected val _event = MutableSharedFlow<E>(replay = 0)
    val event: SharedFlow<E> = _event.asSharedFlow()

}