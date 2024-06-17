package com.carlostorres.apphabits.onboarding.domain.usecases

import com.carlostorres.apphabits.onboarding.domain.repository.OnboardingRepo

class HasSeenOnboardingUseCase(
    private val repo : OnboardingRepo
) {

    operator fun invoke() : Boolean {

        return repo.hasSeenOnboarding()

    }

}