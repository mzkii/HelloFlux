package com.mzkii.dev.flux

import androidx.lifecycle.ViewModel

abstract class Store(
    private val dispatcher: Dispatcher
) : ViewModel() {
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

    protected fun <T> StoreLiveData<T>.postValue(value: T) {
        internalPostValue(value)
    }

    protected fun <T> StoreLiveData<T>.setValue(value: T) {
        internalSetValue(value)
    }
}
