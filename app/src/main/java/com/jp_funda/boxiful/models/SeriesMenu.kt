package com.jp_funda.boxiful.models

import androidx.annotation.StringRes
import com.jp_funda.boxiful.R

enum class SeriesMenu(
    @StringRes
    val titleRes: Int,
    @StringRes
    val description: Int,
    val modules: List<SeriesModule>,
) {
    EveryDayFiveMinutesMenu(
        titleRes = R.string.menu_everyday_five_minutes_title,
        description = R.string.menu_everyday_five_minutes_description,
        modules = listOf(
            SingleMenu.EasyMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.NormalMenu,
        ),
    ),
    OnlyKickMenu(
        titleRes = R.string.menu_only_kick_title,
        description = R.string.menu_only_kick_title,
        modules = listOf(
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.KickMenu,
        ),
    ),
    TrainWellMenu(
        titleRes = R.string.menu_train_well_title,
        description = R.string.menu_train_well_description,
        modules = listOf(
            SingleMenu.EasyMenu,
            Interval.FiveSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.HardMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.NormalMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.EasyMenu,
            Interval.FiveSecondsInterval,
            SingleMenu.KickMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.HardMenu,
            Interval.FifteenSecondsInterval,
            SingleMenu.NormalMenu,
            Interval.FiveSecondsInterval,
            SingleMenu.EasyMenu,
        ),
    );

    val durationInMinutes = modules.sumOf { it.durationInMinutes.toDouble() }.toFloat()
    val calorieConsumption = modules.filterIsInstance<SingleMenu>().sumOf { it.calorieConsumption }
}