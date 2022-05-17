package com.jp_funda.boxiful.data.shared_preference

enum class PreferenceKey(val key: String) {
    // Auth
    REFRESH_TOKEN("refresh token"),
    ACCESS_TOKEN("access token"),
    EMAIL("email"),
    // Settings
    IS_FIRST_LAUNCH_FINISHED("is first launch finished");
}