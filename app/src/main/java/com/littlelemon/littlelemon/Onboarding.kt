package com.littlelemon.littlelemon

import ads_mobile_sdk.h6
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Onboarding() {
    Column {
        // Header mit Logo
        Image(painterResource(id = R.drawable.Logo), contentDescription = "Logo")

        // Text zur Aufforderung
        Text(text = "Let's get to know you", style = MaterialTheme.typography.headlineMedium)

        // Textfelder für Benutzereingaben
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        TextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
        TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

        // Registrieren-Button
        Button(onClick = { /* Registrierungscode hier */ }) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Onboarding()
}
