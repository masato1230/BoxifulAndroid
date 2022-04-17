package com.jp_funda.boxful.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

class PoseImageAnalyzer(private val listener: () -> Unit = {}) : ImageAnalysis.Analyzer {

    // Base pose detector with streaming frames, when depending on the pose-detection sdk
    private val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    private val poseDetector = PoseDetection.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        listener()
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            val result: Task<Pose> = poseDetector.process(image)
            result.addOnSuccessListener {
                Log.d(
                    "right wrist",
                    it.getPoseLandmark(PoseLandmark.LEFT_WRIST)?.position?.x.toString(),
                )
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