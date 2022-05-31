package com.jp_funda.boxiful.data.repository.training_result.entity

import com.squareup.moshi.Json

data class TrainingResult(
    val id: Int? = null, // Do not use this field in this app
    val calorie: Int,
    @Json(name = "created_at")
    val createdAt: String? = null,
    val menu: String,
    val point: Int,
    val score: Int,
    val user: Int? = null, // Do not use this field in this app
)
