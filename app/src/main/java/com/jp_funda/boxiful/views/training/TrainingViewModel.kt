package com.jp_funda.boxiful.views.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.pose.Pose
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.utils.scoring.ScoreCalculator
import com.jp_funda.boxiful.views.components.pose_preview.PoseObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor() : ViewModel(), PoseObserver {
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
}