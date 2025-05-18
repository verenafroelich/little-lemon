package com.littlelemon.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable


@Composable
fun navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
        //startDestination = if (user is logged)
        //{Onboarding.route} else {Home.route}
    ) {
        composable(Onboarding.route) {
            Onboarding()
        }
        composable(Home.route) {
            Home()
        }

    }
}