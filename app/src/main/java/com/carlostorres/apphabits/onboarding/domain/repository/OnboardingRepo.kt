package com.carlostorres.apphabits.onboarding.domain.repository

interface OnboardingRepo {

    fun hasSeenOnboarding() : Boolean

    fun completeOnboarding()

}