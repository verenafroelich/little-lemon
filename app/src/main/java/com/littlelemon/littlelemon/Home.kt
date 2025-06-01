package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {
    Row (){
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(.62f)
                .fillMaxHeight(.12f)
        )
        Image(
            painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .fillMaxWidth(.32f)
                .fillMaxHeight(.12f)
                .clickable(onClick = {
                    navController.navigate("Profile")
                })
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}