package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.flux.ActionCreator
import com.mzkii.dev.flux.Dispatcher

class HomeActionCreator(
    dispatcher: Dispatcher
) : ActionCreator<HomeAction>(dispatcher) {

    fun load() = dispatch(HomeAction.ShowLoading(isLoading = true))
}