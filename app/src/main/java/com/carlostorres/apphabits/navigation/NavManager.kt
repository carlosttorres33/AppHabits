package com.carlostorres.apphabits.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.apphabits.onboarding.ui.OnboardingScreen

@Composable
fun NavManager(
    navController: NavHostController,
    startDestination: NavRoutes
){

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){

        composable(NavRoutes.Onboarding.route){

            //Onboarding
            OnboardingScreen(
                onFinish = {
                    navController.popBackStack()
                    navController.navigate(NavRoutes.Login.route)
                }
            )

        }

        composable(NavRoutes.Login.route){

            //Onboarding
            Text("Login")

        }

    }

}