package com.carlostorres.apphabits.home.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.apphabits.R
import com.carlostorres.apphabits.home.presentation.home.HomeEvents
import com.carlostorres.apphabits.home.presentation.home.HomeViewModel
import com.carlostorres.apphabits.home.ui.home.components.HomeDateSelecter
import com.carlostorres.apphabits.home.ui.home.components.HomeHabit
import com.carlostorres.apphabits.home.ui.home.components.HomeQuote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewHabit : () -> Unit,
    onSettings : () -> Unit,
    onEditHabit : (String) -> Unit
) {

    val state = viewModel.state

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "HOME")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onSettings()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Menu"
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
                    onNewHabit()
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create new habit",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {

            item {

                HomeQuote(
                    quote = "We first make our habits, and then our habits make us.",
                    author = "Anonymous",
                    image = R.drawable.onboarding1,
                    modifier = Modifier.padding(bottom = 19.dp)
                )

            }

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 13.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "HABITS".uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(end = 16.dp)
                    )

                    HomeDateSelecter(
                        selectedDate = state.selectedDate,
                        minDate = state.currentDate,
                        onDateClick = { dateClicked ->
                            viewModel.onEvent(HomeEvents.ChangeDate(dateClicked))
                        }
                    )

                }

            }

            items(state.habits){ habit ->

                Spacer(modifier = Modifier.height(6.dp))
                HomeHabit(
                    habit = habit,
                    selectedDay = state.selectedDate.toLocalDate() ,
                    onCheckChanged = {
                        viewModel.onEvent(HomeEvents.CompleteHabit(habit))
                     },
                    onHabitClick = {
                        onEditHabit(habit.id)
                    }
                )
            }
        }
    }
}
