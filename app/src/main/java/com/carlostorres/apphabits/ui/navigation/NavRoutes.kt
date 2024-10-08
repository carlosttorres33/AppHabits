package com.carlostorres.apphabits.ui.navigation

sealed class NavRoutes (val route : String) {

    object Onboarding : NavRoutes(route = "Onboarding")
    object Login : NavRoutes(route = "Login")
    object Home : NavRoutes(route = "Home")
    object SignUp : NavRoutes(route = "SignUp")
    object Detail : NavRoutes(route = "Detail")
    object Settings : NavRoutes(route = "Settings")

}