package com.alltechies.storyui.data

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
data class ScrollState(
    val startScroll: MutableState<Boolean> = mutableStateOf(false),
    val immediateFill: MutableState<Boolean> = mutableStateOf(false),
    val pauseScroll: Boolean = false,
    val animatable: Animatable<Float, AnimationVector1D> = Animatable(0f)
)
