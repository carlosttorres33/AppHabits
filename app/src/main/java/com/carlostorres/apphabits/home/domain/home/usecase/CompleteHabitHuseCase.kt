package com.carlostorres.apphabits.home.domain.home.usecase

import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import com.carlostorres.apphabits.ui.navigation.NavRoutes
import java.time.ZonedDateTime

class CompleteHabitUseCase(
    private val repo : HomeRepo
) {

    suspend operator fun invoke (habit: Habit, date : ZonedDateTime){
        val newHabit = if (habit.completedDates.contains(date.toLocalDate())){
            habit.copy(
                completedDates = habit.completedDates - date.toLocalDate()
            )
        } else {
            habit.copy(
                completedDates = habit.completedDates + date.toLocalDate()
            )
        }

        repo.upsertHabit(newHabit)
    }

}