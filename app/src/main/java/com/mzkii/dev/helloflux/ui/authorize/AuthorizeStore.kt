package com.mzkii.dev.helloflux.ui.authorize

import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.flux.Store
import com.mzkii.dev.flux.StoreLiveData
import com.mzkii.dev.githubgistclient.data.preferences.AppSetting
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AuthorizeStore(
    private val appSetting: AppSetting,
    dispatcher: Dispatcher
) : Store(dispatcher) {

    val gotoHomeState = StoreLiveData<Unit>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: AuthorizeAction) {
        when (action) {
            is AuthorizeAction.Authorize -> {
                appSetting.accessToken = action.accessToken.accessToken
                gotoHomeState.postValue(Unit)
            }
        }
    }
}