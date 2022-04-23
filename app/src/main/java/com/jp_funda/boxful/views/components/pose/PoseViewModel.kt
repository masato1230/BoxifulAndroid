package com.jp_funda.boxful.views.components.pose

import android.util.Size
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.pose.Pose
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PoseViewModel @Inject constructor() : ViewModel() {

    /** Pose landmarks. */
    private val _pose = MutableLiveData<Pose>()
    val pose: LiveData<Pose> = _pose

    fun setPose(value: Pose) {
        _pose.value = value
    }

    /** Image Analysis Resolution */
    var imageAnalysisResolution = Size(0, 0)
}