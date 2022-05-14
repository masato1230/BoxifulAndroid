package com.jp_funda.boxiful.views.way_to_use

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jp_funda.boxiful.R

enum class WayToUsePage(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val thumbnail: Int,
) {
    GetStarted(
        titleRes = R.string.way_to_use_get_started,
        descriptionRes = R.string.way_to_use_get_started_desc,
        thumbnail = R.drawable.ic_service_thumbnail,
    ),
    AboutJudge(
        titleRes = R.string.way_to_use_about_judge,
        descriptionRes = R.string.way_to_use_about_judge_desc,
        thumbnail = R.drawable.ic_about_punch,
    ),
    AboutAccount(
        titleRes = R.string.way_to_use_about_account,
        descriptionRes = R.string.way_to_use_about_account_desc,
        thumbnail = R.drawable.ic_delete_account,
    ),
    AboutRecord(
        titleRes = R.string.way_to_use_about_record,
        descriptionRes = R.string.way_to_use_about_account_desc,
        thumbnail = R.drawable.ic_record,
    );
}