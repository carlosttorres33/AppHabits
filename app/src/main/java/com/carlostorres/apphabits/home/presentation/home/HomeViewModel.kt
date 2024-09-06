package com.carlostorres.apphabits.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.apphabits.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases : HomeUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getHabits()
        viewModelScope.launch(Dispatchers.IO) {
            useCases.syncHabitUseCase()
        }
    }

    fun onEvent(event : HomeEvents){

        when(event){
            is HomeEvents.ChangeDate -> {
                state = state.copy(selectedDate = event.date)
                getHabits()
            }
            is HomeEvents.CompleteHabit ->{
                viewModelScope.launch {
                    useCases.completeHabitUseCase(event.habit, state.selectedDate)
                }
            }
        }
    }

    private fun getHabits(){
        viewModelScope.launch {
            useCases.getAllHabitsForDatesUseCase(state.selectedDate).collectLatest { habitsListFlow ->
                state = state.copy(
                    habits = habitsListFlow
                )
            }
        }
    }

}