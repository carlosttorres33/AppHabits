package com.carlostorres.apphabits.onboarding.presentation

import androidx.annotation.DrawableRes

data class OnboardingPagerInfo(
    val title : String,
    val subtitle : String,
    @DrawableRes val image: Int
)
