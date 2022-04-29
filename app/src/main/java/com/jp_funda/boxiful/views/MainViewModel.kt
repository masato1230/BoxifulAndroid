package com.jp_funda.boxiful.views

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.SingleMenuScores
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    /** Single Menu scores */
    var singleMenuScores = SingleMenuScores(
        singleMenu = SingleMenu.EasyMenu,
        scores = listOf()
    ) // TODO remove initial value
}