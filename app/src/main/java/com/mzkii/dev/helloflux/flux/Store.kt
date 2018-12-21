package com.mzkii.dev.helloflux.flux

import android.arch.lifecycle.ViewModel

abstract class Store(private val dispatcher: Dispatcher) : ViewModel() {
    init {
        register()
    }

    private fun register() {
        dispatcher.register(this)
    }

    override fun onCleared() {
        dispatcher.unregister(this)
        super.onCleared()
    }
}
