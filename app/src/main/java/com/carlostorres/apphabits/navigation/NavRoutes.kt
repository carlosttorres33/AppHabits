package com.carlostorres.apphabits.navigation

sealed class NavRoutes (val route : String) {

    object Onboarding : NavRoutes(route = "Onboarding")

}