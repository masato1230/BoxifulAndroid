package com.jp_funda.boxiful.views.way_to_use_detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.views.way_to_use.WayToUsePage

enum class WayToUseDetailPage(
    @StringRes val titleRes: Int,
    @StringRes val contentRes: Int,
    @DrawableRes val thumbnailRes: Int,
) {
    AboutThisApp(
        titleRes = R.string.way_to_use_about_this_app,
        contentRes = R.string.way_to_use_about_this_app_content,
        thumbnailRes = R.drawable.ic_service_thumbnail,
    ),
    SelectMenu(
        titleRes = R.string.way_to_use_select_menu,
        contentRes = R.string.way_to_use_select_menu_content,
        thumbnailRes = R.drawable.img_select_menu,
    );

    companion object {
        fun getDetailPages(wayToUsePage: WayToUsePage) : List<WayToUseDetailPage> {
            return when (wayToUsePage) {
                WayToUsePage.GetStarted -> listOf(
                    AboutThisApp,
                    SelectMenu,
                )
                else -> listOf()
            }
        }
    }
}