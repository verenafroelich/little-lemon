package com.littlelemon.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun navigation(navController: NavHostController) {
    var userRegistered = false
    var startDestination = Onboarding.route
    if(userRegistered){
        startDestination = Home.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
        //startDestination = if (user is logged)
        //{Onboarding.route} else {Home.route}
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController)
        }

    }
}