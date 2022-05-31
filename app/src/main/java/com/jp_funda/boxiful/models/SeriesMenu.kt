package com.jp_funda.boxiful.models

import androidx.annotation.StringRes

enum class SeriesMenu(
    @StringRes
    val titleRes: Int,
    @StringRes
    val description: Int,
    val modules: List<SeriesModule>,
    val durationInMinutes: Int,
    val calorieConsumption: Int,
) {
    // TODO
}