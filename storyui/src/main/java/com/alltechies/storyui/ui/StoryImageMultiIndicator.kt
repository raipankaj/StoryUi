package com.alltechies.storyui.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alltechies.storyui.data.Indicator
import com.alltechies.storyui.data.ScrollState


@Composable
fun rememberStoryState(size: Int): Array<ScrollState> {
    return remember {
        Array(size) { ScrollState() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryImageMultipleIndicator(url: String,
                                size: Int,
                                indicator: Indicator,
                                pagerState: PagerState,
                                swipeTime: Int,
                                scrollStateList: Array<ScrollState>,
                                onLeftClick: () -> Unit,
                                onRightClick: () -> Unit,
                                onSwitch: () -> Unit
) {
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(indicator.indicatorPadding),
            horizontalArrangement = Arrangement.spacedBy(indicator.indicatorSpacing)) {
            repeat(size) {
                ProgressLine(
                    scrollState = scrollStateList[it],
                    indicator = indicator,
                    swipeTime = swipeTime,
                    onSwitch = onSwitch
                )
            }
        }

        HorizontalPager(
            userScrollEnabled = false,
            pageCount = size,
            state = pagerState) {
            StoryAsyncImage(url, screenWidth, onLeftClick, onRightClick)
        }
    }
}


@Composable
fun RowScope.ProgressLine(
    scrollState: ScrollState,
    indicator: Indicator,
    swipeTime: Int,
    onSwitch: () -> Unit
) {
    val animatable = scrollState.animatable

    LaunchedEffect(key1 = scrollState.startScroll.value) {
        if (scrollState.startScroll.value) {
            animatable.animateTo(1.0f, animationSpec = tween(swipeTime, easing = LinearEasing))
            onSwitch()
        } else {
            animatable.snapTo(0f)
        }
    }

    LaunchedEffect(key1 = scrollState.immediateFill) {
        if (scrollState.immediateFill.value) {
            if (animatable.isRunning) {
                animatable.stop()
            }
            animatable.snapTo(1f)
            scrollState.immediateFill.value = false
        }
    }

    LinearProgressIndicator(
        progress = animatable.value,
        modifier = Modifier
            .weight(1f)
            .then(indicator.modifier),
        color = indicator.indicatorColor,
        trackColor = indicator.indicatorTrackColor
    )
}
