package com.carlostorres.apphabits.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlostorres.apphabits.core.ui.HabitCheckBox
import com.carlostorres.apphabits.home.domain.model.Habit
import java.time.LocalDate

@Composable
fun HomeHabit(
    modifier: Modifier = Modifier,
    habit: Habit,
    selectedDay : LocalDate,
    onCheckChanged : () -> Unit,
    onHabitClick : () -> Unit
) {

    Row (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                onHabitClick()
            }
            .padding(19.dp),
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Text(
            text = habit.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        
        HabitCheckBox(isChecked = habit.completedDates.contains(selectedDay)) {
            onCheckChanged()
        }

    }

}