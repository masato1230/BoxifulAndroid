package com.jp_funda.boxiful.views.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.pose.Pose
import com.jp_funda.boxiful.AppUtils
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.data.repository.training_result.TrainingResultRepository
import com.jp_funda.boxiful.data.shared_preference.AuthPreferences
import com.jp_funda.boxiful.data.shared_preference.PreferenceKey
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.models.NetworkStatus
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.models.TrainingResultInfo
import com.jp_funda.boxiful.utils.calculator.CalorieCalculator
import com.jp_funda.boxiful.utils.calculator.ScoreCalculator
import com.jp_funda.boxiful.views.components.pose_preview.PoseObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val appUtils: AppUtils,
    private val authPreferences: AuthPreferences,
    private val trainingResultRepository: TrainingResultRepository,
) : ViewModel(), PoseObserver {
    private val _networkStatus = MutableLiveData<NetworkStatus>(NetworkStatus.Waiting)
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    private lateinit var singleMenu: SingleMenu

    /** Punch or Kick Instructions. */
    private val instructions = mutableListOf<Instruction>()

    /** Index of current instruction. */
    private val _instructionIndex = MutableLiveData(0)
    val instructionIndex: LiveData<Int> = _instructionIndex

    /** Getter for current instruction */
    private val currentInstruction: Instruction?
        get() {
            return try {
                instructions[instructionIndex.value!!]
            } catch (_: Exception) {
                null
            }
        }

    /** Scores. */
    private val scores = mutableListOf<Int>()

    /** Whether current instruction's movement is started. */
    private var isMoveStarted = false

    /** Start time of current movement. */
    private var moveStartTime = Date().time

    /** TrainingResult. */
    private val trainingResultInfo: TrainingResultInfo
        get() {
            return TrainingResultInfo(
                menu = singleMenu.name,
                calorie = CalorieCalculator.getCaloriesBurned(instructions),
                point = scores.sum() / 10,
                score = ScoreCalculator.getSingleMenuOverallScore(scores),
            )
        }

    /** Getter for single menu. */
    fun getSingleMenu(): SingleMenu {
        return singleMenu
    }

    /** set menu and generate corresponding instructions. */
    fun setSingleMenu(menu: SingleMenu) {
        singleMenu = menu
        generateInstructions(menu)
    }

    /** Getter for instructions */
    fun getInstructions(): List<Instruction> {
        return instructions
    }

    /** Get for scores. */
    fun getScores(): List<Int> {
        return scores
    }

    /** Generate instructions from given single menu and update livedata value. */
    private fun generateInstructions(menu: SingleMenu) {
        instructions.clear()
        for (i in 1..menu.numOfInstructions) {
            val randomlySelectedInstruction = menu.instructionTypes.random()
            instructions.add(randomlySelectedInstruction)
        }
    }

    /**
     * Pose Preview Callback.
     * called from PosePreview when pose is detected. (about 30 ~ 60fps)
     */
    override fun onPoseUpdated(pose: Pose) {
        // Before start movement
        if (!isMoveStarted) {
            moveStartTime = Date().time
            isMoveStarted = currentInstruction?.detectStartCallback?.invoke(pose) ?: false
        } else { // During movement
            val isMoveEnd = currentInstruction?.detectEndCallback?.invoke(pose) ?: false
            if (isMoveEnd) {
                // Record score
                val score =
                    ScoreCalculator.getSingleMenuMoveScore(time = (Date().time - moveStartTime) / 1000f)
                scores.add(score)
                isMoveStarted = false
                // Increment instruction index to show next instruction
                _instructionIndex.value = _instructionIndex.value!! + 1
            }
        }
    }

    /** Post training result to server. */
    fun registerTrainingResult() {
        // Do nothing when user is not logged in
        if (!appUtils.isLoggedIn) return

        // Do nothing when already loading or load finished
        if (_networkStatus.value != NetworkStatus.Waiting) return

        viewModelScope.launch {
            _networkStatus.value = NetworkStatus.Loading
            try {
            val isSucceed = trainingResultRepository.postTrainingResult(
                accessToken = authPreferences.getString(PreferenceKey.ACCESS_TOKEN)!!,
                trainingResultInfo = trainingResultInfo
            )

            if (isSucceed) _networkStatus.value = NetworkStatus.Success
            else _networkStatus.value = NetworkStatus.Error(R.string.error_connect_server)
            } catch (e: Exception) {
                _networkStatus.value = NetworkStatus.Error(R.string.error_connect_server)
                e.printStackTrace()
            }
        }
    }
}