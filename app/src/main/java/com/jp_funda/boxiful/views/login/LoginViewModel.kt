package com.jp_funda.boxiful.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp_funda.boxiful.data.repository.auth.AuthRepository
import com.jp_funda.boxiful.models.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
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
                _networkStatus.value = NetworkStatus.Success("ログインに成功しました。")
            } else {
                _networkStatus.value = NetworkStatus.Error("メールアドレスかパスワードが空です。")
            }
        }
    }
}