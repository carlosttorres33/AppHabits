package com.carlostorres.apphabits.home.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.apphabits.core.ui.HabitTextfield
import com.carlostorres.apphabits.home.presentation.detail.DetailEvents
import com.carlostorres.apphabits.home.presentation.detail.DetailViewModel
import com.carlostorres.apphabits.home.ui.detail.components.DetailFrequency
import com.carlostorres.apphabits.home.ui.detail.components.DetailReminder
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    //habitID : String?,
    onBack : () -> Unit,
    onSave : () -> Unit
) {

    val state = viewModel.state

    val clockState = rememberSheetState()
    
    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes{ hours, minutes ->
             viewModel.onEvent(DetailEvents.ReminderChanged(LocalTime.of(hours, minutes)))
        },
        config = ClockConfig(
            defaultTime = state.reminder,
            is24HourFormat = true
        )
    )

    LaunchedEffect(state.isSaved) {
        if (state.isSaved){
            onSave()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "NEW HABIT")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    viewModel.onEvent(DetailEvents.HabitSaved)
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Create new habit",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    ){ paddingValues ->
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            
            HabitTextfield(
                value = state.habitTitle,
                onValueChange = { text ->
                    viewModel.onEvent(DetailEvents.TitleChanged(text))
                },
                placeholder = "New Habit",
                contentDescription = "Enter Habit title",
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions{
                    viewModel.onEvent(DetailEvents.HabitSaved)
                }
            )

            DetailFrequency(
                selectedDays = state.frequency,
                onFrequencyChange = { dayOfWeek ->
                    viewModel.onEvent(DetailEvents.FrequencyChanged(dayOfWeek))
                }
            )
            
            DetailReminder(reminder = state.reminder) {
                clockState.show()
            }

        }
    }
}