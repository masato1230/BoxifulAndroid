package com.jp_funda.boxiful.views.home

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
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.TrainingResultInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appUtils: AppUtils,
    private val trainingResultRepository: TrainingResultRepository,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    val isLoggedIn: Boolean
        get() {
            return appUtils.isLoggedIn
        }

    private var trainingResults: List<TrainingResultInfo>? = null

    private val todayTrainings: List<TrainingResultInfo>?
        get() {
            return trainingResults?.filter { it.createdAt == LocalDate.now() }
        }

    val todayNumberOfTrainings: Int
        get() {
            return todayTrainings?.filter { it.createdAt == LocalDate.now() }?.size ?: 0
        }

    val todayBoxifulPoints: Int
        get() {
            return todayTrainings?.sumOf { it.calorie } ?: 0
        }

    val todayCalorieConsumption: Int
        get() {
            return todayTrainings?.sumOf { it.calorie } ?: 0
        }

    private var selectedSingleMenu: SingleMenu = SingleMenu.NormalMenu

    fun setSelectedSingleMenu(menu: SingleMenu) {
        selectedSingleMenu = menu
    }

    fun getSelectedSingleMenu(): SingleMenu {
        return selectedSingleMenu
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