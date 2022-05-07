package com.jp_funda.boxiful.models

import java.time.LocalDate

data class TrainingResultInfo(
    val menu: String,
    val calorie: Int,
    val point: Int,
    val createdAt: LocalDate = LocalDate.now(),
    val score: Int,
)