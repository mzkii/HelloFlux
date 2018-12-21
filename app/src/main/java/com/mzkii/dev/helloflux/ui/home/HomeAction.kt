package com.mzkii.dev.helloflux.ui.home

sealed class HomeAction {

    class ShowLoading(val isLoading: Boolean) : HomeAction()
}