package com.carlostorres.apphabits.home.ui.home.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.carlostorres.apphabits.core.ui.HabitButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAskPermission(
    modifier: Modifier = Modifier,
    permission: String,
) {

    val permissionState = rememberPermissionState(permission = permission)

    LaunchedEffect(key1 = Unit) {

        permissionState.launchPermissionRequest()

    }

    if (permissionState.status.shouldShowRationale){
        
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { /*TODO*/ },
            confirmButton = { 
                HabitButton(text = "Accept", modifier = Modifier.fillMaxWidth()) {
                    permissionState.launchPermissionRequest()
                }
            },
            title = {
                Text(text = "PermissionRequired")
            },
            text = {
                Text(text = "We need this permission for the app to work correctly")
            }
        )
        
    }

}