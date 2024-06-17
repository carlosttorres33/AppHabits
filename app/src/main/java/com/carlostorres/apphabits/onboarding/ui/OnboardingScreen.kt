package com.carlostorres.apphabits.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.apphabits.R
import com.carlostorres.apphabits.onboarding.presentation.OnboardingViewModel
import com.carlostorres.apphabits.onboarding.presentation.model.OnboardingPagerInfo
import com.carlostorres.apphabits.onboarding.ui.components.OnboardingPager

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
){

    LaunchedEffect(viewModel.hasSeenOnboarding){

        if (viewModel.hasSeenOnboarding){
            onFinish()
        }

    }

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
        viewModel.completeOnboarding()
    }

}