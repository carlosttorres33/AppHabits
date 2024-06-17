package com.carlostorres.apphabits.navigation

sealed class NavRoutes (val route : String) {

    object Onboarding : NavRoutes(route = "Onboarding")
    object Login : NavRoutes(route = "Login")

}