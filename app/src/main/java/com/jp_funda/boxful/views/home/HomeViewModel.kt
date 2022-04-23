package com.jp_funda.boxful.views.home

import androidx.lifecycle.ViewModel
import com.jp_funda.boxful.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appUtils: AppUtils,
) : ViewModel() {
    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }
}