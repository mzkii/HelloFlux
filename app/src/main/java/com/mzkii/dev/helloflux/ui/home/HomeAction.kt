package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.helloflux.data.api.response.Repository

sealed class HomeAction {

    class ShowLoading(val isLoading: Boolean) : HomeAction()
    class LoadRepositoryList(val repositoryList: List<Repository>) : HomeAction()
}