package com.mzkii.dev.helloflux.flux

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.meta.SubscriberInfoIndex
import timber.log.Timber

class Dispatcher(index: SubscriberInfoIndex?) {
    private val bus = EventBus.builder()
        .throwSubscriberException(true)
        .also { if (index != null) it.addIndex(index) }
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
