package com.jp_funda.boxiful.models

sealed class NetworkStatus {
    object Waiting : NetworkStatus()
    object Loading : NetworkStatus()
    data class Success<T>(val response: T) : NetworkStatus()
    data class Error(val errorMessage: String) : NetworkStatus()
}