package com.jp_funda.boxful

import javax.inject.Inject

class AppUtils @Inject constructor() {

    /** Whether user is logged in to boxiful backend server. */
    val isLoggedIn: Boolean
        get() {
            // TODO check cookie
            return false
        }
}