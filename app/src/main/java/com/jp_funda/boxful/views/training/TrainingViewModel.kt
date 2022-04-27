package com.jp_funda.boxful.views.training

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jp_funda.boxful.models.Instruction
import com.jp_funda.boxful.models.SingleMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor() : ViewModel() {
    private lateinit var singleMenu: SingleMenu

    /** Punch or Kick Instructions. */
    private val instructions = mutableListOf<Instruction>()

    /** Index of current instruction. */
    private val _instructionIndex = MutableLiveData(0)
    val instructionIndex: LiveData<Int> = _instructionIndex

    fun getSingleMenu() : SingleMenu {
        return singleMenu
    }

    /** set menu and generate corresponding instructions. */
    fun setSingleMenu(menu: SingleMenu) {
        singleMenu = menu
        generateInstructions(menu)
    }

    /** Generate instructions from given single menu and update livedata value. */
    private fun generateInstructions(menu: SingleMenu) {
        for (i in 1..menu.numOfInstructions) {
            val randomlySelectedInstruction = menu.instructionTypes.random()
            Log.d("Instruction", randomlySelectedInstruction.name)
            instructions.add(randomlySelectedInstruction)
        }
    }
}