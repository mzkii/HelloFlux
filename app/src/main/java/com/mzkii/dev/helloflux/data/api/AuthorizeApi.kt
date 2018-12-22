package com.mzkii.dev.helloflux.data.api

import com.mzkii.dev.helloflux.data.api.response.AccessToken
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizeApi {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String) : Single<AccessToken>
}