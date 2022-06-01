package com.jp_funda.boxiful.models

import androidx.annotation.StringRes
import com.jp_funda.boxiful.R

enum class Interval(
    @StringRes
    override val titleRes: Int,
    override val durationInMinutes: Float,
) : SeriesModule {
    FifteenSecondsInterval(
        titleRes = R.string.interval_fifteen_seconds,
        durationInMinutes = 0.25f,
    );
}