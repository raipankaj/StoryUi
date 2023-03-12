package com.alltechies.storyui

sealed class IndicatorType {
    object Single: IndicatorType()
    object Multiple: IndicatorType()
}