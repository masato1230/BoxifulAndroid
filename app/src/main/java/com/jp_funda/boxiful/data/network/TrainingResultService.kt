package com.jp_funda.boxiful.data.network

import com.jp_funda.boxiful.data.repository.training_result.entity.TrainingResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface TrainingResultService {

    @GET("training_results")
    suspend fun fetchTrainingResults(@HeaderMap headers: Map<String, String>): Response<List<TrainingResult>>

    @POST("training_results")
    suspend fun postTrainingResults(
        @HeaderMap headers: Map<String, String>,
        trainingResult: TrainingResult,
    ): Response<Unit>
}