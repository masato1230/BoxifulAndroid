package com.jp_funda.boxful.models

import androidx.annotation.StringRes

/** Menu for one term training. */
enum class SingleMenu(
    @StringRes
    val titleRes: Int,
    @StringRes
    val descriptionRes: Int,
    val InstructionTypes: List<Instruction>,
    val calorieConsumption: Int,
    val durationInMinutes: Int,
) {
    /**
     * Easy Menu.
     * Menu which contains only punch instructions
     */
}