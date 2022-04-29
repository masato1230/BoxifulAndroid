package com.jp_funda.boxiful.views.components.pose_preview

import android.annotation.SuppressLint
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

class PoseImageAnalyzer(
    private val poseGraphicViewModel: PoseGraphicViewModel,
    private val poseObservers: List<PoseObserver>,
) : ImageAnalysis.Analyzer {

    // Base pose detector with streaming frames, when depending on the pose-detection sdk
    private val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    private val poseDetector = PoseDetection.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            val result: Task<Pose> = poseDetector.process(image)

            // update viewModels pose landmarks
            result.addOnSuccessListener {
                // Port lait mode
                if (mediaImage.width > mediaImage.height) {
                    poseGraphicViewModel.imageAnalysisResolution =
                        Size(mediaImage.height, mediaImage.width)
                } else {
                    poseGraphicViewModel.imageAnalysisResolution =
                        Size(mediaImage.width, mediaImage.height)
                }
                poseGraphicViewModel.setPose(it)
                poseObservers.forEach { observer ->
                    observer.onPoseUpdated(it)
                }
            }

            result.addOnFailureListener {
                it.printStackTrace()
            }

            // Release image resource to process next analysis
            result.addOnCompleteListener {
                imageProxy.close()
            }
        }
    }
}