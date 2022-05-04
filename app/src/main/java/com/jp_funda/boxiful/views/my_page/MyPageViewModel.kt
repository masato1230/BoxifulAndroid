package com.jp_funda.boxiful.views.my_page

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authPreferences: AuthPreferences,
) : ViewModel() {

    /** Logged in user's email. */
    val email: String
        get() {
            return authPreferences.getString(PreferenceKey.EMAIL) ?: ""
        }
}