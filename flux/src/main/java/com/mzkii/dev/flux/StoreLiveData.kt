package com.mzkii.dev.flux

import androidx.lifecycle.LiveData

class StoreLiveData<T> : LiveData<T>() {
    internal fun internalPostValue(value: T) {
        postValue(value)
    }

    internal fun internalSetValue(v: T) {
        value = v
    }
}

// https://satoshun.github.io/2018/12/livedata_helper/