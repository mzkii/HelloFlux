package com.mzkii.dev.flux

import org.greenrobot.eventbus.EventBus
import timber.log.Timber

class Dispatcher {
    private val bus = EventBus.builder()
        .throwSubscriberException(true)
        .build()

    fun dispatch(event: Any) {
        Timber.tag(this::class.java.simpleName).d("dispatch event=$event")
        bus.post(event)
    }

    fun register(observer: Any) {
        Timber.tag(this::class.java.simpleName).d("register observer=$observer")
        bus.register(observer)
    }

    fun unregister(observer: Any) {
        Timber.tag(this::class.java.simpleName).d("unregister observer=$observer")
        bus.unregister(observer)
    }
}
