package com.jp_funda.boxiful.data.shared_preference

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsPreferences @Inject constructor(context: Context) {
    companion object {
        const val PREF_NAME = "Settings"
    }

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getBoolean(key: PreferenceKey): Boolean {
        return preferences.getBoolean(key.key, false)
    }

    fun putBoolean(key: PreferenceKey, value: Boolean) {
        preferences.edit().putBoolean(key.key, value).apply()
    }
}