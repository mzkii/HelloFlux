package com.mzkii.dev.helloflux.ui.authorize

import com.mzkii.dev.helloflux.data.api.response.AccessToken

sealed class AuthorizeAction {
    class Authorize(val accessToken: AccessToken) : AuthorizeAction()
}