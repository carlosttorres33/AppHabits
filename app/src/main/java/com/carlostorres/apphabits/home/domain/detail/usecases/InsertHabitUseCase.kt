package com.carlostorres.apphabits.home.domain.detail.usecases

import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo

class InsertHabitUseCase(
    private val repo : HomeRepo
) {

    suspend operator fun invoke (habit: Habit){
        repo.upsertHabit(habit)
    }

}