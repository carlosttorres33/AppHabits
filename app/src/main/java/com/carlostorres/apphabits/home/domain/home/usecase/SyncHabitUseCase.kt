package com.carlostorres.apphabits.home.domain.home.usecase

import com.carlostorres.apphabits.home.repository.HomeRepo

class SyncHabitUseCase(private val repository: HomeRepo) {
    suspend operator fun invoke() = repository.syncHabits()
}