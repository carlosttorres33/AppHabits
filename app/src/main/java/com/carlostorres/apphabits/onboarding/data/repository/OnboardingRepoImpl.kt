package com.carlostorres.apphabits.onboarding.data.repository

import android.content.SharedPreferences
import com.carlostorres.apphabits.onboarding.domain.repository.OnboardingRepo

class OnboardingRepoImpl(
    private val sharedPreferences: SharedPreferences
) : OnboardingRepo {

    companion object{
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }

}