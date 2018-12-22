package com.mzkii.dev.helloflux.data.api.response

import com.squareup.moshi.Json

data class AccessToken(
    @field:Json(name = "access_token") val accessToken: String,
    @field:Json(name = "token_type") val tokenType: String
)