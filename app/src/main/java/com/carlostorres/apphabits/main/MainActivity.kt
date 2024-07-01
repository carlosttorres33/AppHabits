package com.carlostorres.apphabits.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.carlostorres.apphabits.ui.navigation.NavManager
import com.carlostorres.apphabits.ui.navigation.NavRoutes
import com.carlostorres.apphabits.ui.theme.AppHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            AppHabitsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavManager(
                        navController,
                        getStartDestination()
                    )

                }
            }
        }
    }

    private fun getStartDestination() : NavRoutes {

        if(viewModel.isLoggedIn){
            return NavRoutes.Home
        }
        return if (viewModel.hasSeenOnboarding){
            NavRoutes.Login
        }else{
            NavRoutes.Onboarding
        }

    }

}