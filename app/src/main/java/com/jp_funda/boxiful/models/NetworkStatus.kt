package com.jp_funda.boxiful.models

import androidx.annotation.StringRes

sealed class NetworkStatus {
    object Waiting : NetworkStatus()
    object Loading : NetworkStatus()
    data class Success<T>(val response: T) : NetworkStatus()
    data class Error(@StringRes val errorRes: Int) : NetworkStatus()
}