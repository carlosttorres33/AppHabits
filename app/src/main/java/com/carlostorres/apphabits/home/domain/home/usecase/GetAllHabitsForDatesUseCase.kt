package com.carlostorres.apphabits.home.domain.home.usecase

import com.carlostorres.apphabits.home.domain.model.Habit
import com.carlostorres.apphabits.home.repository.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

class GetAllHabitsForDatesUseCase(
    private val repo : HomeRepo
) {

    operator fun invoke(date : ZonedDateTime) : Flow<List<Habit>> {
        return repo.getAllHabitsForSelectedDate(date).map {
            it.filter {
                it.frequency.contains(date.dayOfWeek)
            }
        }.distinctUntilChanged()
    }

}