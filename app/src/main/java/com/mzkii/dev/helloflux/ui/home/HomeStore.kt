package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.flux.Store
import com.mzkii.dev.flux.StoreLiveData
import com.mzkii.dev.helloflux.data.api.response.Repository
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class HomeStore(dispatcher: Dispatcher) : Store(dispatcher) {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    private val repositoryList = mutableListOf<Repository>()

    var canFetchMore = false
        private set

    var pageNum = INITIAL_PAGE
        private set

    val loadingState = StoreLiveData<Boolean>()
    val loadedRepositoryListState = StoreLiveData<List<Repository>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: HomeAction) {
        when (action) {

            is HomeAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is HomeAction.LoadRepositoryList -> {
                canFetchMore = action.repositoryList.isNotEmpty()
                pageNum++
                repositoryList.addAll(action.repositoryList)
                loadedRepositoryListState.postValue(repositoryList)
            }
        }
    }
}