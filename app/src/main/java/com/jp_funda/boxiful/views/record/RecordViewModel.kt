package com.jp_funda.boxiful.views.record

import androidx.lifecycle.ViewModel
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.data.repository.training_result.TrainingResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val appUtils: AppUtils,
    private val trainingResultRepository: TrainingResultRepository,
) : ViewModel() {
}