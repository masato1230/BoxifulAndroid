package com.jp_funda.boxiful.data.repository.training_result.entity

import com.squareup.moshi.Json

data class TrainingResult(
    val calorie: Int,
    @Json(name = "created_at")
    val createdAt: String,
    val id: Int,
    val menu: String,
    val point: Int,
    val score: Int,
    val user: Int,
)
