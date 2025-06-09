package com.littlelemon.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    Column {
        // Header mit Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(0.dp)
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth(.62f)
                    .fillMaxHeight(.12f)
            )
        }

        // Text zur Aufforderung
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.width(300.dp)
                .padding(start = 30.dp, end = 30.dp)
                .height(70.dp),
                //.background(Color(0xFF495E57))
            contentAlignment = Alignment.Center
        )

        {}
        Text("Profilinformationen", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
        // Textfelder für Benutzereingaben
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier
                .padding(top = 16.dp, bottom = 5.dp)
                .fillMaxWidth())
            {Text("First Name")}
            Box(
                modifier = Modifier
                    .border(border=BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .fillMaxWidth()
                    //.width(300.dp)
                    .height(30.dp),
                contentAlignment = Alignment.CenterStart
            )
            {
                Text(
                    sharedPreferences.getString("firstName", "").toString()
                )
            }
            Box(modifier = Modifier
                .padding(top = 16.dp, bottom = 5.dp)
                .fillMaxWidth())
            {Text("Last Name")}
            Box(
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .fillMaxWidth()
                    //.width(300.dp)
                    .height(30.dp),
                contentAlignment = Alignment.CenterStart
            )
            {
                Text(
                    sharedPreferences.getString("lastName", "").toString()
                )
            }
            Box(modifier = Modifier
                .padding(top = 16.dp, bottom = 5.dp)
                .fillMaxWidth())
            {Text("Email")}
            Box(
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .fillMaxWidth()
                    //.width(300.dp)
                    .height(30.dp),
                contentAlignment = Alignment.CenterStart
            )
            {
                Text(
                    sharedPreferences.getString("email", "").toString()
                )
            }

            Button(modifier = Modifier.padding(top = 250.dp,start = 30.dp, end = 30.dp )
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    //delete firstname, lastname from shared preferences
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                    //navigate to Onboarding
                    navController.navigate("Onboarding")}
            ) {
                Text("LogOut", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    val navController = rememberNavController()
    Profile(navController = navController)
}