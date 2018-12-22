package com.mzkii.dev.helloflux.ui.authorize

import android.content.Intent
import com.mzkii.dev.flux.ActionCreator
import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.helloflux.BuildConfig
import com.mzkii.dev.helloflux.data.repository.AuthorizeRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthorizeActionCreator(
    private val repository: AuthorizeRepository,
    dispatcher: Dispatcher
) : ActionCreator<AuthorizeAction>(dispatcher) {
    fun authorize(intent: Intent) {
        val uri = intent.data ?: return
        if (!uri.toString().startsWith(BuildConfig.REDIRECT_URI)) return
        repository
            .getAccessToken(uri.getQueryParameter("code")!!)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { dispatch(AuthorizeAction.Authorize(it)) },
                onError = { Timber.e(it) }
            )
    }
}