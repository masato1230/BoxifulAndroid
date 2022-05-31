package com.jp_funda.boxiful.data.shared_preference

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthPreferences @Inject constructor(context: Context) {
    companion object {
        const val PREF_NAME = "Auth"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getString(key: PreferenceKey): String? {
        return preferences.getString(key.key, null)
    }

    fun putString(key: PreferenceKey, value: String) {
        preferences.edit().putString(key.key, value).apply()
    }
}