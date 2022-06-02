package com.jp_funda.boxiful.models

import androidx.annotation.StringRes

interface SeriesModule {
    @get: StringRes
    val titleRes: Int
    val durationInMinutes: Float
}