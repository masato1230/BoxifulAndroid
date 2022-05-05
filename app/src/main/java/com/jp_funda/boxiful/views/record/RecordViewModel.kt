package com.jp_funda.boxiful.views.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.data.repository.training_result.TrainingResultRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.models.TrainingResultInfo
import com.jp_funda.boxiful.utils.date.DateIterator
import com.jp_funda.boxiful.views.components.calendar_heat_map.CalendarHeatmapLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val appUtils: AppUtils,
    private val trainingResultRepository: TrainingResultRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    private var trainingResults: List<TrainingResultInfo>? = null

    val resultStartDate: LocalDate = LocalDate.now().minusDays(180)

    /** LocalDate - CalendarHeatmapLevel map for draw graph. */
    val dateTrainingLevelMap: Map<LocalDate, CalendarHeatmapLevel>
        get() {
            val pointMap = mutableMapOf<LocalDate, Int>()
            trainingResults?.let {
                it.forEach { trainingResultInfo ->
                    pointMap[trainingResultInfo.createdAt] =
                        trainingResultInfo.point + (pointMap[trainingResultInfo.createdAt] ?: 0)
                }
            }
            return pointMap.map { originalKeyValue ->
                val index = originalKeyValue.value / 200
                val level =
                    if (index < CalendarHeatmapLevel.values().size) {
                        CalendarHeatmapLevel.values()[index]
                    } else {
                        CalendarHeatmapLevel.Level10
                    }
                originalKeyValue.key to level
            }.toMap()
        }

    /** LocalDate - CalendarHeatmapLevel map for draw graph. */
    val dateTextsMap: Map<LocalDate, List<String>>
        get() {
            val textsMap = mutableMapOf<LocalDate, List<String>>()
            val dateIterator = DateIterator(resultStartDate, LocalDate.now(), 1)

            dateIterator.forEach { date ->
                trainingResults?.filter { it.createdAt == date }?.let { resultsInDate ->
                    textsMap[date] = listOf(
                        "消費したカロリー ${resultsInDate.sumOf { it.calorie }}kcal", // TODO use string resource
                        "Boxifulポイント ${resultsInDate.sumOf { it.point }}ポイント", // TODO use string resource
                    )
                }
            }
            return textsMap
        }

    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }

    fun getTrainingResults() {
        // Do nothing when already loaded
        if (_networkStatus.value != NetworkStatus.Waiting) return

        _networkStatus.value = NetworkStatus.Loading
        viewModelScope.launch {
            try {
                trainingResults = trainingResultRepository.fetchTrainingResults(
                    authPreferences.getString(PreferenceKey.ACCESS_TOKEN)!!
                )
                if (trainingResults != null) {
                    _networkStatus.value = NetworkStatus.Success
                } else {
                    _networkStatus.value =
                        NetworkStatus.Error(errorRes = R.string.error_connect_server)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _networkStatus.value = NetworkStatus.Error(errorRes = R.string.error_connect_server)
            }
        }
    }
}