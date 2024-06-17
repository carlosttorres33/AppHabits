package com.carlostorres.apphabits.onboarding.domain.usecases

import com.carlostorres.apphabits.onboarding.domain.repository.OnboardingRepo

class CompleteOnboardingUseCase (
    private val repo: OnboardingRepo
) {

    operator fun invoke() {
        repo.completeOnboarding()
    }

}