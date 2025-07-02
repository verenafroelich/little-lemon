package com.littlelemon.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun navigation(navController: NavHostController) {
    var userRegistered = false
    var startDestination = Onboarding.route
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.getString("firstName", "").toString()


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
        composable(Profile.route) {
            Profile(navController)
        }
    }
}