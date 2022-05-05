package com.jp_funda.boxiful.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp_funda.boxiful.data.repository.auth.AuthRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.SingleMenuResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for MainActivity.
 * use this view model only for sharing large data between screens or concerns of activity.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {

    /** Single Menu scores */
    var singleMenuScores = SingleMenuResult(
        singleMenu = SingleMenu.EasyMenu,
        scores = listOf(),
        instructions = listOf()
    )

    /** Refresh cached auth tokens. */
    fun refreshAuthTokens() {
        viewModelScope.launch {
            try {
                val refreshToken = authPreferences.getString(PreferenceKey.REFRESH_TOKEN)
                if (!refreshToken.isNullOrBlank()) {
                    val response = authRepository.fetchTokens(refreshToken)

                    // When token session is valid
                    response?.let {
                        authPreferences.putString(PreferenceKey.REFRESH_TOKEN, it.refreshToken)
                        authPreferences.putString(PreferenceKey.ACCESS_TOKEN, it.accessToken)
                    } ?: run { // When token timeout -> reset tokens
                        authPreferences.apply {
                            putString(PreferenceKey.REFRESH_TOKEN, "")
                            putString(PreferenceKey.ACCESS_TOKEN, "")
                            putString(PreferenceKey.EMAIL, "")
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}