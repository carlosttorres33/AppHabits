package com.carlostorres.apphabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.apphabits.authentication.ui.login.LoginScreen
import com.carlostorres.apphabits.authentication.ui.signup.SignUpScreen
import com.carlostorres.apphabits.home.ui.detail.DetailScreen
import com.carlostorres.apphabits.home.ui.home.HomeScreen
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
            LoginScreen(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(NavRoutes.Home.route)
                },
                onSignUp = {
                    navController.navigate(NavRoutes.SignUp.route)
                }
            )

        }
        //endregion

        // region Home
        composable(NavRoutes.Home.route){

            //Home
            HomeScreen(
                onSettings = {
                    //TODO
                },
                onNewHabit = {
                    navController.navigate(NavRoutes.Detail.route)
                }
            )

        }
        //endregion

        // region SignUp
        composable(NavRoutes.SignUp.route){

            //SignUp
            SignUpScreen(
                onSignedIn = {
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                onLogin = {
                    navController.popBackStack()
                }
            )

        }
        //endregion

        // region Detail
        composable(NavRoutes.Detail.route){
            
            DetailScreen(
                onBack = {

                },
                onSave = {

                }
            )

        }
        //endregion

    }

}