package com.jp_funda.boxiful.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jp_funda.boxiful.R

enum class SeriesMenu(
    @StringRes
    val titleRes: Int,
    @StringRes
    val descriptionRes: Int,
    val modules: List<SeriesModule>,
) {
    EveryDayFiveMinutesMenu(
        titleRes = R.string.menu_everyday_five_minutes_title,
        descriptionRes = R.string.menu_everyday_five_minutes_description,
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
        descriptionRes = R.string.menu_only_kick_title,
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
        descriptionRes = R.string.menu_train_well_description,
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

    @DrawableRes
    fun getThumbnailRes(): Int {
        return when (this) {
            EveryDayFiveMinutesMenu -> R.drawable.ic_menu_easy
            OnlyKickMenu -> R.drawable.ic_menu_normal
            TrainWellMenu -> R.drawable.ic_menu_hard
        }
    }

    companion object {
        fun fromName(name: String?): SeriesMenu? = values().find { it.name == name }
    }
}