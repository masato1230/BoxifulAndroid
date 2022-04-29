package com.jp_funda.boxiful.views.components.pose_preview

import com.google.mlkit.vision.pose.Pose

interface PoseObserver {
    fun updatePose(pose: Pose)
}