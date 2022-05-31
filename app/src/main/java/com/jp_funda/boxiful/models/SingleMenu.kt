package com.jp_funda.boxiful.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jp_funda.boxiful.R

/** Menu for one term training. */
enum class SingleMenu(
    @StringRes
    override val titleRes: Int,
    @StringRes
    val descriptionRes: Int,
    val instructionTypes: Set<Instruction>,
    val calorieConsumption: Int,
    val durationInMinutes: Int,
    val numOfInstructions: Int,
) : SeriesModule {
    /**
     * Easy Menu.
     * Menu which contains only punch instructions
     */
    EasyMenu(
        titleRes = R.string.menu_easy_title,
        descriptionRes = R.string.menu_easy_description,
        instructionTypes = setOf(
            Instruction.LeftHandLeftPunch,
            Instruction.LeftHandRightPunch,
            Instruction.RightHandLeftPunch,
            Instruction.RightHandRightPunch,
        ),
        calorieConsumption = 9,
        durationInMinutes = 1,
        numOfInstructions = 30,
    ),
    /**
     * Normal Menu.
     * Menu which contains punch and kick instructions
     */
    NormalMenu(
        titleRes = R.string.menu_normal_title,
        descriptionRes = R.string.menu_normal_description,
        instructionTypes = Instruction.values().toSet(),
        calorieConsumption = 15,
        durationInMinutes = 2,
        numOfInstructions = 30,
    ),
    /**
     * Hard Menu.
     * Menu which is longer than normal menu
     */
    HardMenu(
        titleRes = R.string.menu_hard_title,
        descriptionRes = R.string.menu_hard_description,
        instructionTypes = Instruction.values().toSet(),
        calorieConsumption = 45,
        durationInMinutes = 3,
        numOfInstructions = 100,
    ),
    /**
     * Kick Menu.
     * Menu which contains only kick instructions
     */
    KickMenu(
        titleRes = R.string.menu_kick_title,
        descriptionRes = R.string.menu_kick_description,
        instructionTypes = setOf(
            Instruction.LeftFootLeftKick,
            Instruction.LeftFootRightKick,
            Instruction.RightFootLeftKick,
            Instruction.RightFootRightKick,
        ),
        calorieConsumption = 18,
        durationInMinutes = 2,
        numOfInstructions = 30,
    );

    @DrawableRes
    fun getThumbnail(): Int {
        return when (this) {
            EasyMenu -> R.drawable.ic_menu_easy
            KickMenu -> R.drawable.ic_menu_normal
            NormalMenu -> R.drawable.ic_menu_normal
            HardMenu -> R.drawable.ic_menu_hard
        }
    }

    companion object {
        fun fromName(name: String?): SingleMenu? = values().find { it.name == name }
    }
}