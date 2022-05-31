package com.jp_funda.boxiful.models

import androidx.annotation.StringRes

enum class Interval(
    @StringRes
    override val titleRes: Int,
    val durationInSeconds: Int,
) : SeriesModule