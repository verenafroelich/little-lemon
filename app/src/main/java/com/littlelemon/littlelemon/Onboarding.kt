package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun Onboarding() {
    Column {
        // Header mit Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
        Image(painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
            .fillMaxWidth(.32f)
            .fillMaxHeight(.12f)

        )}
        HorizontalDivider(
            modifier = Modifier.padding(0.dp),
            color = Color.Gray,
            thickness = 1.dp
        )

        // Text zur Aufforderung
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))

        ){Text(text = "Let's get to know you", fontSize = 20.sp)}

        // Textfelder für Benutzereingaben
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        TextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
        TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

        // Registrieren-Button
        Button(onClick = { /* Registrierungscode hier */ },
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),
            shape = RoundedCornerShape(10.dp),
            //modifier = Modifier
             //  .align(Alignment.Center)
            ) {
            Text("Register")
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Onboarding()
}
