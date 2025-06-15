package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {
    Column (modifier = Modifier
        .padding(top = 56.dp, bottom = 5.dp)
        .fillMaxWidth()){
        Row {
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
        Box(modifier = Modifier
            .padding(top = 16.dp, bottom = 5.dp)
            .fillMaxWidth()
            .background(color = Color(0xFF495E57))

        ) {
                Text("Little Lemon", fontSize = 30.sp, modifier = Modifier.padding(16.dp), color = Color.Yellow)
                Text("Chicago", fontSize = 20.sp, modifier = Modifier.padding(start = 16.dp, top = 66.dp))
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 5.dp)
                    .fillMaxWidth()
            )
            { Text("Last Name") }
            }
        }




@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}