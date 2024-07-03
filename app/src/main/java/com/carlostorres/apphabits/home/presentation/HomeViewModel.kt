package com.carlostorres.apphabits.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    fun onEvent(event : HomeEvents){

        when(event){
            is HomeEvents.ChangeDate -> state = state.copy(selectedDate = event.date)
            is HomeEvents.CompleteHabit -> TODO()
        }

    }

}