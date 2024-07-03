package com.carlostorres.apphabits.core.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HabitCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckChanged: () -> Unit
) {

    val backgroundColor = if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background

    Box(
        modifier = modifier
            .size(35.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .clickable {
                onCheckChanged()
            },
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(
            targetState = isChecked, label = ""
        ) {
            if (it) {

                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = MaterialTheme.colorScheme.tertiary
                )

            }
        }
    }
}

@Preview
@Composable
private fun PHCB() {
    Column {
        HabitCheckBox(isChecked = true) {}
        HabitCheckBox(isChecked = false) {}
    }
}