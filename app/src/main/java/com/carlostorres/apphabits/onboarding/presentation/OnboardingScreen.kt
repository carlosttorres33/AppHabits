package com.carlostorres.apphabits.onboarding.presentation

import androidx.compose.runtime.Composable
import com.carlostorres.apphabits.R
import com.carlostorres.apphabits.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
){

    val pages = listOf(
        OnboardingPagerInfo(
            title = "Welcome To Monumental Habits",
            subtitle = "We can help you to be a better version of yourself.",
            image = R.drawable.onboarding1,
        ),
        OnboardingPagerInfo(
            title = "Welcome To Monumental Habits",
            subtitle = "Create new habits easily.",
            image = R.drawable.onboarding2,
        ),
        OnboardingPagerInfo(
            title = "Welcome To Monumental Habits",
            subtitle = "Keep track of your progress.",
            image = R.drawable.onboarding3,
        ),
        OnboardingPagerInfo(
            title = "Welcome To Monumental Habits",
            subtitle = "Join a supportive community.",
            image = R.drawable.onboarding4,
        )
    )

    OnboardingPager(
        pages = pages,
    ){
        onFinish()
    }

}