package com.carlostorres.apphabits.home.domain.detail.usecases

import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo

class GetHabitByIdUseCase(
    private val repo: HomeRepo
) {

    suspend operator fun invoke(id : String) : Habit{
        return repo.getHabitById(id)
    }

}