package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.flux.Store
import com.mzkii.dev.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class HomeStore(dispatcher: Dispatcher) : Store(dispatcher) {

    val loadingState = StoreLiveData<Boolean>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action : HomeAction) {
        when(action) {
            is HomeAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
        }
    }
}