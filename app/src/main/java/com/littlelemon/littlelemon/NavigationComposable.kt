package com.littlelemon.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun navigation(navController: NavController){
    val navController = rememberNavController()
    navHost(
        navController = navController,
        startDestination = if (user is logged)
        {Onboarding.route} else {Home.route}
    )

}