package com.carlostorres.apphabits.home.domain.detail.usecases

import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHabitByIdUseCase(
    private val repo: HomeRepo
) {

    suspend operator fun invoke(id : String) : Habit{
        return withContext(Dispatchers.IO){
            repo.getHabitById(id)
        }
    }

}