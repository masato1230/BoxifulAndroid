package com.jp_funda.boxiful.views.components.header

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeaderViewModel @Inject constructor(private val appUtils: AppUtils) : ViewModel() {
    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }
}