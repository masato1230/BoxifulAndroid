package com.jp_funda.boxiful.views.series_training

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.models.SeriesMenu

class SeriesTrainingViewModel: ViewModel() {
    private lateinit var seriesMenu: SeriesMenu

    fun setSeriesMenu(menu: SeriesMenu) {
        seriesMenu = menu
    }
}