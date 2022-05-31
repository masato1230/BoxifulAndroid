package com.jp_funda.boxiful.data.network

import com.jp_funda.boxiful.data.repository.auth.entity.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthService {

    @POST("users/token/refresh/")
    suspend fun fetchTokens(@Body request: RefreshTokensRequest): Response<RefreshTokensResponse>

    @POST("users/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("users/register/")
    suspend fun register(@Body request: LoginRequest): Response<RegisterResponse>

    @DELETE("users/delete/")
    suspend fun deleteAccount(@HeaderMap headers: Map<String, String>): Response<DeleteAccountResponse>
}