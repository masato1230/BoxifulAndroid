package com.jp_funda.boxiful.views.login

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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        _networkStatus.value = NetworkStatus.Loading
        viewModelScope.launch {
            // TODO use input data
            if (!_email.value.isNullOrBlank() && !_password.value.isNullOrBlank()) {
                val tokenInfo = authRepository.login(_email.value!!, _password.value!!)
                // Update network status
                if (tokenInfo != null) {
                    // Cache tokens
                    authPreferences.putString(PreferenceKey.ACCESS_TOKEN, tokenInfo.accessToken)
                    authPreferences.putString(PreferenceKey.REFRESH_TOKEN, tokenInfo.refreshToken)
                    _networkStatus.value = NetworkStatus.Success(tokenInfo)
                } else {
                    _networkStatus.value =
                        NetworkStatus.Error(R.string.error_invalid_email_or_password)
                }
            } else {
                _networkStatus.value = NetworkStatus.Error(R.string.error_empty_email_or_password)
            }
        }
    }
}