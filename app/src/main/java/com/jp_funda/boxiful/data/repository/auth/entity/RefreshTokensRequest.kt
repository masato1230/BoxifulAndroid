package com.jp_funda.boxiful.data.repository.auth.entity

import com.squareup.moshi.Json

data class RefreshTokensRequest(
    @Json(name = "refresh")
    val refreshToken: String
)