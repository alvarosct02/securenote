package com.asct94.securenote.features.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    fun <T> Flow<T>.collectWhenStarted(collector: FlowCollector<T>) =
        collectWithLifecycle(Lifecycle.State.STARTED, collector)

    private fun <T> Flow<T>.collectWithLifecycle(
        state: Lifecycle.State,
        collector: FlowCollector<T>
    ) {
        val flow = this
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                flow.collect(collector)
            }
        }
    }

    abstract fun setupViews()
    abstract fun setupObservers()

}