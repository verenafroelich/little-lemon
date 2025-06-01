package com.littlelemon.littlelemon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home() {
    Row {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(.32f)
                .fillMaxHeight(.12f)
        )
        Image(
            painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .fillMaxWidth(.32f)
                .fillMaxHeight(.12f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home()
}