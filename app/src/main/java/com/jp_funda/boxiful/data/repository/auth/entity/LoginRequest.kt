package com.jp_funda.boxiful.data.repository.auth.entity

data class LoginRequest(
    val email: String,
    val password: String,
)