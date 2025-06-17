package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavHostController) {
    val menuItems = remember { val menuItems by db.menuDao().getAllMenuItems()
        observeAsState(emptyList()) }
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        var searchPhrase by remember { mutableStateOf(TextFieldValue("")) }
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
        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .background(color = Color(0xFF495E57))

        ) {
            Text(
                "Little Lemon",
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp),
                color = Color.Yellow
            )
            Text(
                "Chicago",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 66.dp, bottom = 150.dp),
                color = Color.White
            )
            Row(
                Modifier
                    .fillMaxWidth(),
                // horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 10.dp)
                        .fillMaxWidth(.32f),
                        color = Color.White
                )
                Image(
                    painterResource(id = R.drawable.hero),
                    contentDescription = "Hero",
                    modifier = Modifier
                        .fillMaxWidth(.85f)
                        .fillMaxHeight(.52f)
                        .offset(y = 106.dp),
                    alignment = Alignment.CenterEnd,
                )
            }
         }

        MenuItems(menuItems = MenuItems())

        OutlinedTextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, // Verwenden Sie das Standard-Such-Icon
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
             .padding(start = 15.dp, end = 15.dp),
            shape = RoundedCornerShape(18.dp)
        )
        }
}

@Composable
fun MenuItems(menuItems: Unit){

    Column {
        for(item in menuItems){
            //MenuItems(menuItems)
            MenuItemView(menuItems)
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemView(item: MenuItem) {
    Column (Modifier.padding(8.dp)){
        Text(text = item.title, fontWeight = FontWeight.Bold)
        Text(text = "${item.price} €")
        Text(text = item.description)
        GlideImage(
            model = item.image, // URL des Bildes
            contentDescription = item.title, // Beschreibung für Barrierefreiheit
            modifier = Modifier
                .size(100.dp) // Größe des Bildes
                .clip(RoundedCornerShape(8.dp)), // Abgerundete Ecken
            contentScale = ContentScale.Crop // Bild zuschneiden
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}