package com.jp_funda.boxful.views.training

import androidx.lifecycle.ViewModel
import com.jp_funda.boxful.models.SingleMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor() : ViewModel() {
    private lateinit var singleMenu: SingleMenu

    fun getSingleMenu() : SingleMenu {
        return singleMenu
    }

    fun setSingleMenu(menu: SingleMenu) {
        singleMenu = menu
    }
}