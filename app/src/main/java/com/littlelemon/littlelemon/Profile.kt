package com.littlelemon.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Profile() {
    Column {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(.32f)
                .fillMaxHeight(.12f)
        )
        {Text(text = "Profilinformationen")}
    }
    Column (modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
        { Text(with(sharedPreferences.edit()) {
            getString("firstName", firstName)commit()}) }
        { Text(with(sharedPreferences.edit()) {
            getString("lastName", lastName)commit()}) }
        { Text(with(sharedPreferences.edit()) {
            getString("lastName", lastName)commit()}) }

        Button (modifier = Modifier.padding(top = 250.dp,start = 30.dp, end = 30.dp )
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                    // Navigiere zur Home-Seite
                    navController.navigate("Onboarding")
                }
            }) {
            Text("Abmelden")
        }

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    Profile()
}