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
    // Get Started pages
    AboutThisApp(
        titleRes = R.string.way_to_use_about_this_app,
        contentRes = R.string.way_to_use_about_this_app_content,
        thumbnailRes = R.drawable.ic_service_thumbnail,
    ),
    SelectMenu(
        titleRes = R.string.way_to_use_select_menu,
        contentRes = R.string.way_to_use_select_menu_content,
        thumbnailRes = R.drawable.img_select_menu,
    ),
    GrantCameraUse(
        titleRes = R.string.way_to_use_grant_camera_use,
        contentRes = R.string.way_to_use_grant_camera_use_content,
        thumbnailRes = R.drawable.img_permission,
    ),
    StartTraining(
        titleRes = R.string.way_to_use_start_training,
        contentRes = R.string.way_to_use_start_training_content,
        thumbnailRes = R.drawable.img_kicking,
    ),
    CheckTrainingResult(
        titleRes = R.string.way_to_use_check_result,
        contentRes = R.string.way_to_use_check_result_content,
        thumbnailRes = R.drawable.img_result,
    ),
    RecordTraining(
        titleRes = R.string.way_to_use_record_training,
        contentRes = R.string.way_to_use_record_training_content,
        thumbnailRes = R.drawable.img_record,
    ),
    // About Judge pages
    AboutPunchJudge(
        titleRes = R.string.way_to_use_about_punch_judge,
        contentRes = R.string.way_to_use_about_punch_judge_content,
        thumbnailRes = R.drawable.ic_about_punch,
    ),
    AboutKickJudge(
        titleRes = R.string.way_to_use_about_kick_judge,
        contentRes = R.string.way_to_use_about_kick_judge_content,
        thumbnailRes = R.drawable.ic_about_kick,
    ),
    NotRecognizeMovement(
        titleRes = R.string.way_to_use_not_recognize_movement,
        contentRes = R.string.way_to_use_not_recognize_movement_content,
        thumbnailRes = R.drawable.ic_question,
    ),
    CameraAndImageProcessing(
        titleRes = R.string.way_to_use_camera_and_image_processing,
        contentRes = R.string.way_to_use_camera_and_image_processing_content,
        thumbnailRes = R.drawable.ic_camera,
    ),
    // About Account
    AvailableByAccount(
        titleRes = R.string.way_to_use_available_by_account,
        contentRes = R.string.way_to_use_available_by_account_content,
        thumbnailRes = R.drawable.img_total_record,
    ),
    DeleteAccount(
        titleRes = R.string.way_to_use_delete_account,
        contentRes = R.string.way_to_use_delete_account_content,
        thumbnailRes = R.drawable.ic_delete_account,
    )
    ;

    companion object {
        fun getDetailPages(wayToUsePage: WayToUsePage) : List<WayToUseDetailPage> {
            return when (wayToUsePage) {
                WayToUsePage.GetStarted -> listOf(
                    AboutThisApp,
                    SelectMenu,
                    GrantCameraUse,
                    StartTraining,
                    CheckTrainingResult,
                    RecordTraining,
                )
                WayToUsePage.AboutJudge -> listOf(
                    AboutPunchJudge,
                    AboutKickJudge,
                    NotRecognizeMovement,
                    CameraAndImageProcessing,
                )
                WayToUsePage.AboutAccount -> listOf(
                    AvailableByAccount,
                    DeleteAccount,
                )
                else -> listOf()
            }
        }
    }
}