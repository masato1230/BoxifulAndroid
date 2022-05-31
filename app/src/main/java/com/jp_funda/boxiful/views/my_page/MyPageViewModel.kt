package com.jp_funda.boxiful.views.my_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.data.repository.auth.AuthRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import com.jp_funda.boxiful.models.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    /** Logged in user's email. */
    val email: String
        get() {
            return authPreferences.getString(PreferenceKey.EMAIL) ?: ""
        }

    /** Logout. */
    fun logout() {
        authPreferences.apply {
            putString(PreferenceKey.EMAIL, "")
            putString(PreferenceKey.ACCESS_TOKEN, "")
            putString(PreferenceKey.REFRESH_TOKEN, "")
        }
    }

    /** Delete account. */
    fun deleteAccount() {
        _networkStatus.value = NetworkStatus.Loading
        viewModelScope.launch {
            try {
                val isSuccessDelete =
                    authRepository.deleteAccount(authPreferences.getString(PreferenceKey.ACCESS_TOKEN)!!)
                if (isSuccessDelete) {
                    // Logout when succeed - Remove email, access token and refresh token cach
                    logout()
                    _networkStatus.value = NetworkStatus.Success
                } else {
                    _networkStatus.value =
                        NetworkStatus.Error(errorRes = R.string.error_connect_server)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _networkStatus.value = NetworkStatus.Error(errorRes = R.string.error_connect_server)
            }
        }
    }
}