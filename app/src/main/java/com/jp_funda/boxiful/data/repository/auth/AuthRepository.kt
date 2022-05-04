package com.jp_funda.boxiful.data.repository.auth

import com.jp_funda.boxiful.data.network.AuthService
import com.jp_funda.boxiful.data.repository.auth.entity.LoginRequest
import com.jp_funda.boxiful.data.repository.auth.entity.RefreshTokensRequest
import com.jp_funda.boxiful.models.TokenInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {

    suspend fun login(email: String, password: String): TokenInfo? {
        val request = LoginRequest(email, password)
        val response = authService.login(request)

        // Return null when failed
        return if (!response.isSuccessful || response.body() == null) null
        else TokenInfo(
            refreshToken = response.body()!!.refreshToken,
            accessToken = response.body()!!.accessToken,
        )
    }

    /**
     * @return register success or not.
     */
    suspend fun register(email: String, password: String): Boolean {
        val request = LoginRequest(email, password)
        val response = authService.register(request)

        // Return null when failed
        return response.isSuccessful
    }

    /**
     * Fetch tokens.
     * @param refreshToken cached refresh token
     * @return return fetched tokens or null when failed.
     */
    suspend fun fetchTokens(refreshToken: String): TokenInfo? {
        val response = authService.fetchTokens(RefreshTokensRequest(refreshToken))
        return if (!response.isSuccessful || response.body() == null) null
        else TokenInfo(
            refreshToken = response.body()!!.refreshToken,
            accessToken = response.body()!!.accessToken,
        )
    }
}