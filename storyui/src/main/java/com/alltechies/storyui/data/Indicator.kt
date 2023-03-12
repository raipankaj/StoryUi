package com.alltechies.storyui.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alltechies.storyui.IndicatorType

@Immutable
class Indicator internal constructor(
    val type: IndicatorType = IndicatorType.Single,
    val indicatorColor: Color = Color.White,
    val indicatorTrackColor: Color = Color.Gray,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val indicatorPadding: PaddingValues = PaddingValues(),
    val indicatorSpacing: Dp = 4.dp
)