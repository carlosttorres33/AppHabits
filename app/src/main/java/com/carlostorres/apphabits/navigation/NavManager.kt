package com.carlostorres.apphabits.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.apphabits.authentication.ui.login.LoginScreen
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

        //region Onboarding
        composable(NavRoutes.Onboarding.route){

            OnboardingScreen(
                onFinish = {
                    navController.popBackStack()
                    navController.navigate(NavRoutes.Login.route)
                }
            )

        }
        //endregion

        //region Login
        composable(NavRoutes.Login.route){

            //Login
            LoginScreen()

        }
        //endregion

    }

}