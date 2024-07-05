package com.carlostorres.apphabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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

                },
                onNewHabit = {
                    navController.navigate(NavRoutes.Detail.route)
                },
                onEditHabit = { habitID ->
                    navController.navigate(NavRoutes.Detail.route + "?habitID=$habitID")
                }
            )

        }
        //endregion

        // region SignUp
        composable(
            route = NavRoutes.SignUp.route
        ){

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
        composable(
            route = NavRoutes.Detail.route + "?habitID={habitID}",
            arguments = listOf(
                navArgument("habitID"){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            
            DetailScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.popBackStack()
                }
            )

        }
        //endregion

    }

}