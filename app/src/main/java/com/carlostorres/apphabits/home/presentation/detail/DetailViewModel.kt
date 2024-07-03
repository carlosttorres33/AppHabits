package com.carlostorres.apphabits.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    fun onEvent(event : DetailEvents){

        when(event){

            is DetailEvents.FrequencyChanged -> TODO()
            DetailEvents.HabitSaved -> TODO()
            is DetailEvents.ReminderChanged -> TODO()
            is DetailEvents.TitleChanged -> TODO()

        }
    }
}