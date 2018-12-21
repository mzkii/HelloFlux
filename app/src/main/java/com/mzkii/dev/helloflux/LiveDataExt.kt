package com.mzkii.dev.helloflux

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer<T> {
        it?.let { observer(it) }
    })
}

// https://kuluna.github.io/blog/post/20180705/