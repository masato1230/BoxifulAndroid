package com.jp_funda.boxiful.views

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.SingleMenuScores
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for MainActivity.
 * use this view model only for sharing large data between screens or concerns of activity.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    /** Single Menu scores */
    var singleMenuScores = SingleMenuScores(
        singleMenu = SingleMenu.EasyMenu,
        scores = listOf(),
        instructions = listOf()
    ) // TODO remove initial value
}