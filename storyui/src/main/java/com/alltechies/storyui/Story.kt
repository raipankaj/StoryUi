package com.alltechies.jetstory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alltechies.storyui.IndicatorType
import com.alltechies.storyui.data.Indicator
import com.alltechies.storyui.data.StoryIndicator
import com.alltechies.storyui.ui.StoryImageMultipleIndicator
import com.alltechies.storyui.ui.StoryImageSingleIndicator
import com.alltechies.storyui.ui.rememberStoryState
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Story(
    modifier: Modifier = Modifier,
    urlList: List<String>,
    swipeTime: Int = 5_000,
    indicator: Indicator = StoryIndicator.singleIndicator(),
    onAllStoriesShown: () -> Unit
) {

    if (urlList.isEmpty()) throw Exception("List of image urls cannot be empty")

    var currentImageUrl by remember {
        mutableStateOf(urlList.first())
    }

    val state = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scrollStateList = rememberStoryState(urlList.size)

    LaunchedEffect(key1 = Unit) {
        scrollStateList[state.currentPage].startScroll.value = true
    }

    if (indicator.type == IndicatorType.Single) {
        StoryImageSingleIndicator(
            modifier = modifier,
            indicator = indicator,
            state = state,
            size = urlList.size,
            currentImageUrl = currentImageUrl,
            swipeTiming = swipeTime,
            onLeftClick = {
                val currentPage = state.currentPage
                if (currentPage != 0) {
                    currentImageUrl = urlList[currentPage - 1]

                    coroutineScope.launch {
                        state.animateScrollToPage(currentPage - 1)
                    }
                }
            },
            onRightClick = {
                val currentPage = state.currentPage
                if (currentPage + 1 < urlList.size) {
                    currentImageUrl = urlList[currentPage + 1]

                    coroutineScope.launch {
                        state.animateScrollToPage(currentPage + 1)
                    }
                } else {
                    onAllStoriesShown()
                }
            }
        )
    } else {
        StoryImageMultipleIndicator(
            url = currentImageUrl,
            size = urlList.size,
            indicator = indicator,
            pagerState = state,
            scrollStateList = scrollStateList,
            swipeTime = swipeTime,
            onLeftClick = {
                val currentPage = state.currentPage
                if (currentPage != 0) {
                    scrollStateList[currentPage].startScroll.value = false
                    scrollStateList[currentPage - 1].startScroll.value = false
                    currentImageUrl = urlList[currentPage - 1]

                    coroutineScope.launch {
                        state.animateScrollToPage(currentPage - 1)
                        scrollStateList[currentPage - 1].startScroll.value = true
                    }
                }
            },
            onRightClick = {
                val currentPage = state.currentPage

                if (currentPage + 1 < urlList.size) {
                    scrollStateList[currentPage + 1].startScroll.value = true

                    currentImageUrl = urlList[currentPage + 1]

                    coroutineScope.launch {
                        scrollStateList[currentPage].animatable.snapTo(1f)
                    }

                    coroutineScope.launch {
                        state.animateScrollToPage(currentPage + 1)
                    }
                } else {
                    onAllStoriesShown()
                }
            },
            onSwitch = {
                val currentPage = state.currentPage
                if (currentPage + 1 < urlList.size) {
                    scrollStateList[currentPage + 1].startScroll.value = true
                    currentImageUrl = urlList[currentPage + 1]

                    coroutineScope.launch {
                        state.animateScrollToPage(currentPage + 1)
                    }
                } else {
                    onAllStoriesShown()
                }
            }
        )
    }
}