package com.mzkii.dev.helloflux.data.repository

import com.mzkii.dev.helloflux.BuildConfig
import com.mzkii.dev.helloflux.data.api.AuthorizeApi
import com.mzkii.dev.helloflux.data.api.response.AccessToken
import io.reactivex.Single

class AuthorizeRepository(
    private val authorizeApi: AuthorizeApi
) {
    fun getAccessToken(code: String): Single<AccessToken> =
        authorizeApi.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code)
}