package com.jp_funda.boxiful.data.network

import com.jp_funda.boxiful.data.repository.auth.entity.LoginRequest
import com.jp_funda.boxiful.data.repository.auth.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

//    @POST("users/token/refresh")
//    suspend fun fetchRefreshToken(): Response<RefreshToken>

    @POST("users/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}