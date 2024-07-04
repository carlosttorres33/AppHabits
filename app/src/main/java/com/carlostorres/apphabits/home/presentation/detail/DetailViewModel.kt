package com.carlostorres.apphabits.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.apphabits.home.domain.detail.usecases.DetailUseCases
import com.carlostorres.apphabits.home.domain.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCases : DetailUseCases
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        val id = savedStateHandle.get<String?>("habitID")

        if (id != null){
            viewModelScope.launch {
                val habit = useCases.getHabitByIdUseCase(id)
                state = state.copy(
                    id = habit.id,
                    habitTitle = habit.title,
                    reminder = habit.reminder,
                    frequency = habit.frequency,
                    completedDates = habit.completedDates,
                    startDate = habit.startDate
                )
                println()
            }
        }
    }

    fun onEvent(event : DetailEvents){

        when(event){

            is DetailEvents.FrequencyChanged -> {
                val frequency = if(state.frequency.contains(event.dayOfWeek)){
                    state.frequency - event.dayOfWeek
                } else {
                    state.frequency + event.dayOfWeek
                }
                state = state.copy(frequency = frequency)
            }
            DetailEvents.HabitSaved -> {
                viewModelScope.launch {
                    val habit = Habit(
                        id = state.id ?: UUID.randomUUID().toString(),
                        title = state.habitTitle,
                        frequency = state.frequency,
                        completedDates = state.completedDates,
                        reminder = state.reminder,
                        startDate = state.startDate
                    )
                    useCases.insertHabitUseCase(habit)
                }
                state = state.copy(isSaved = true)
            }
            is DetailEvents.ReminderChanged -> state = state.copy(reminder = event.time)
            is DetailEvents.TitleChanged -> state = state.copy(habitTitle = event.title)

        }
    }
}