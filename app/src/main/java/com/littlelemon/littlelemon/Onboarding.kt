package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

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

            HorizontalDivider(
            modifier = Modifier.padding(start=30.dp, end=30.dp),
            color = Color.Gray,
            thickness = 1.dp
        )


        // Text zur Aufforderung
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.width(300.dp)
                .padding(start = 30.dp, end = 30.dp)
                .height(70.dp)
                .background(Color(0xFF495E57)),
           contentAlignment = Alignment.Center
        )

        { Text("Let's get to know you", fontSize = 20.sp, color = Color.White
            ) }

        // Textfelder für Benutzereingaben
        Column(modifier = Modifier.padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally) {
            //{Text(text = "Personal Information")}
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            )
            TextField(
                value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                     ,
                shape = RoundedCornerShape(8.dp)
            )

            TextField(
                value = email, onValueChange = { email = it }, label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height (6.dp))

            // Registrieren-Button
            val context = LocalContext.current
            Button(modifier = Modifier.padding(top = 250.dp,start = 30.dp, end = 30.dp )
                                      .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
                    shape = RoundedCornerShape(8.dp),
                   onClick = {
                  val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(
                        context,
                        "Registrierung fehlgeschlagen. Bitte geben Sie alle Daten ein.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Speichere die Werte in SharedPreferences
                    with(sharedPreferences.edit()) {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        commit()
                    }

                    // Navigiere zur Home-Seite
                    navController.navigate("Home")
                }
            }) {
                Text("Register", color = Color.Black)
            }
        }
    }
}


    @Preview(showBackground = true)
    @Composable
    fun PreviewOnboarding() {
        val navController = rememberNavController()
        Onboarding(navController = navController)
    }

