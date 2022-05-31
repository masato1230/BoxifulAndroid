package com.jp_funda.boxiful

import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import javax.inject.Inject

class AppUtils @Inject constructor(
    private val authPreferences: AuthPreferences,
) {

    /** Whether user is logged in to boxiful backend server. */
    val isLoggedIn: Boolean
        get() {
            return !authPreferences.getString(PreferenceKey.ACCESS_TOKEN).isNullOrBlank() &&
                    !authPreferences.getString(PreferenceKey.REFRESH_TOKEN).isNullOrBlank()
        }
}