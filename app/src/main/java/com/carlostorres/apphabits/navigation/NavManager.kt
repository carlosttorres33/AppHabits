package com.carlostorres.apphabits.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlostorres.apphabits.onboarding.presentation.OnboardingScreen

@Composable
fun NavManager(
    navController: NavHostController,
    startDestination: String
){

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        composable(NavRoutes.Onboarding.route){

            //Onboarding
            OnboardingScreen(
                onFinish = {
                    println("Termino onboarding")
                }
            )

        }

    }

}