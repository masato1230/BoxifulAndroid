package com.jp_funda.boxiful.data.repository.auth.entity

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "access")
    val accessToken: String,
    @Json(name = "refresh")
    val refreshToken: String,
)
