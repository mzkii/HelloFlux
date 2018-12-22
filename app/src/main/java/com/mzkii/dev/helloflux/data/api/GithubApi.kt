package com.mzkii.dev.helloflux.data.api

import com.mzkii.dev.helloflux.data.api.response.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubApi {

    @GET("/user/repos")
    fun getMyRepositoryList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<Repository>>
}