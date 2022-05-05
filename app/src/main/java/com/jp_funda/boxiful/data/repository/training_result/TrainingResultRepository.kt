package com.jp_funda.boxiful.data.repository.training_result

import com.jp_funda.boxiful.data.network.TrainingResultService
import com.jp_funda.boxiful.models.TrainingResultInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingResultRepository @Inject constructor(
    private val trainingResultService: TrainingResultService,
) {
    suspend fun fetchTrainingResults(accessToken: String): List<TrainingResultInfo>? {
        val response =
            trainingResultService.fetchTrainingResults(mapOf("Authorization" to "JWT $accessToken"))
        return if (!response.isSuccessful || response.body() == null) null
        else response.body()!!.map {
            TrainingResultInfo(
                calorie = it.calorie,
                point = it.point,
                createdAt = LocalDate.parse(it.createdAt, DateTimeFormatter.ISO_DATE_TIME),
            )
        }
    }
}