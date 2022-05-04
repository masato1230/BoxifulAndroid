package com.jp_funda.boxiful.views.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.data.repository.training_result.TrainingResultRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val appUtils: AppUtils,
    private val trainingResultRepository: TrainingResultRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }

    fun getTrainingResults() {
        viewModelScope.launch {
            trainingResultRepository.fetchTrainingResults(authPreferences.getString(PreferenceKey.ACCESS_TOKEN)!!)
        }
    }
}