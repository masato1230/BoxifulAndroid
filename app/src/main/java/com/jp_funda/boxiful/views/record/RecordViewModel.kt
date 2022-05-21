package com.jp_funda.boxiful.views.record

import android.app.Application
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
import com.jp_funda.github_heatmap.models.GitHubHeatmapLevel
import com.jp_funda.github_heatmap.utils.DateIterator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val application: Application,
    private val appUtils: AppUtils,
    private val trainingResultRepository: TrainingResultRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    private var trainingResults: List<TrainingResultInfo>? = null
    private val trainingResultsInWeek: List<TrainingResultInfo>?
        get() {
            val dateBeforeOneWeek = LocalDate.now().minusWeeks(1)
            return trainingResults?.filter { it.createdAt.isAfter(dateBeforeOneWeek) }
        }

    val resultStartDate: LocalDate = LocalDate.now().minusDays(90)

    /** Total number of trainings. - for total stats */
    val totalNumberOfTrainings: Int
        get() {
            return trainingResults?.size ?: 0
        }

    /** Total boxiful points. - for total stats */
    val totalBoxifulPoints: Int
        get() {
            return trainingResults?.sumOf { it.point } ?: 0
        }

    /** Total calorie consumption. - for total stats */
    val totalCalorieConsumption: Int
        get() {
            return trainingResults?.sumOf { it.calorie } ?: 0
        }

    /** Weekly number of training. -for weekly stats */
    val weeklyNumberOfTrainings: Int
        get() {
            return trainingResultsInWeek?.count() ?: 0
        }

    /** Weekly boxiful points. -for weekly stats */
    val weeklyBoxifulPoints: Int
        get() {
            return trainingResultsInWeek?.sumOf { it.point } ?: 0
        }

    /** Weekly calorie consumption. -for weekly stats */
    val weeklyCalorieConsumption: Int
        get() {
            return trainingResultsInWeek?.sumOf { it.calorie } ?: 0
        }

    /** LocalDate & CalendarHeatmapLevel map for draw graph. */
    var dateTrainingLevelMap: Map<LocalDate, GitHubHeatmapLevel> = mapOf()

    /** LocalDate & Texts map for cell popup. */
    var dateTextsMap: Map<LocalDate, List<String>> = mapOf()

    /** Getter of calorie consumption of the day. */
    private fun getOneDayCalorieConsumption(date: LocalDate): Int {
        return trainingResults?.filter { it.createdAt == date }?.sumOf { it.calorie } ?: 0
    }

    val dateCaloriePairsInWeek: List<Pair<LocalDate, Int>>
        get() {
            val today = LocalDate.now()
            val dateIterator = DateIterator(
                startDate = today.minusWeeks(1).plusDays(1),
                endDateInclusive = today,
                stepDays = 1,
            )
            val dateCaloriePairs = mutableListOf<Pair<LocalDate, Int>>()
            dateIterator.forEach {
                dateCaloriePairs.add(it to getOneDayCalorieConsumption(it))
            }
            return dateCaloriePairs
        }

    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }

    /** Get Training Results form server and map it to easy to use data. */
    fun getTrainingResults() {
        // Do nothing when already loaded
        if (_networkStatus.value != NetworkStatus.Waiting) return

        _networkStatus.value = NetworkStatus.Loading
        viewModelScope.launch {
            try {
                trainingResults = trainingResultRepository.fetchTrainingResults(
                    authPreferences.getString(PreferenceKey.ACCESS_TOKEN)!!
                )
                // data translations
                trainingResults?.let {
                    dateTrainingLevelMap = translateTrainingResultsToTrainingLevelMap(it)
                    dateTextsMap = translateTrainingResultsToDateTextsMap(it)
                }
                // update fags
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

    /** Translate training result -> training level map. */
    private fun translateTrainingResultsToTrainingLevelMap(results: List<TrainingResultInfo>): Map<LocalDate, GitHubHeatmapLevel> {
        val pointMap = mutableMapOf<LocalDate, Int>()
        results.forEach { trainingResultInfo ->
            pointMap[trainingResultInfo.createdAt] =
                trainingResultInfo.point + (pointMap[trainingResultInfo.createdAt] ?: 0)
        }
        return pointMap.map { originalKeyValue ->
            val index = originalKeyValue.value / 200
            val level =
                if (index < GitHubHeatmapLevel.values().size) {
                    GitHubHeatmapLevel.values()[index]
                } else {
                    GitHubHeatmapLevel.Level10
                }
            originalKeyValue.key to level
        }.toMap()
    }

    /** Translate training result -> description text map. */
    private fun translateTrainingResultsToDateTextsMap(results: List<TrainingResultInfo>): Map<LocalDate, List<String>> {
        val textsMap = mutableMapOf<LocalDate, List<String>>()
        val dateIterator = DateIterator(resultStartDate, LocalDate.now(), 1)

        dateIterator.forEach { date ->
            results.filter { it.createdAt == date }.let { resultsInDate ->
                textsMap[date] = listOf(
                    application.getString(
                        R.string.record_calorie_consumption,
                        resultsInDate.sumOf { it.calorie }),
                    application.getString(
                        R.string.record_boxiful_point,
                        resultsInDate.sumOf { it.point }),
                )
            }
        }
        return textsMap
    }
}