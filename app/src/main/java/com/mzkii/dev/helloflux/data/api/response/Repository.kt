package com.mzkii.dev.helloflux.data.api.response

import com.squareup.moshi.Json

data class Repository(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "full_name") val fullName: String?,
    @field:Json(name = "html_url") val url: String?
)