package com.littlelemon.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlin.text.category

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val menuItems by db.menuItemDao().getAllMenuItems().observeAsState(emptyList())
    //Log.d("HomeScreen", "MenuItems loaded: ${menuItems.size}")
    //Log.d("DEBUG", "MenuItems in DB: ${menuItems.size}")
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
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
                    .fillMaxWidth(.62f)
                    .fillMaxHeight(.12f)
                    .clickable(onClick = {
                 navController.navigate("Profile")
                    })
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 36.dp)
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
                    modifier = Modifier.padding(start = 16.dp, top = 100.dp)
                        .fillMaxWidth(.32f),
                        color = Color.White
                )
                Image(
                    painterResource(id = R.drawable.hero),
                    contentDescription = "Hero",
                    modifier = Modifier
                        .fillMaxWidth(.85f)
                        .fillMaxHeight(.32f)
                        .padding(bottom = 26.dp)
                        .offset(y = 106.dp),
                    alignment = Alignment.CenterEnd,
                )
            }
         }

        var searchPhrase by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf<String?>(null) }
        OutlinedTextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            placeholder = { Text(text = "Enter Search Phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
             .padding(start = 15.dp, end = 15.dp),
            shape = RoundedCornerShape(18.dp)
        )

        CategoryBar(
            categories = listOf("Starters", "Mains", "Desserts", "Drinks"),
            selected = selectedCategory,
            onCategorySelected = { selected ->
                selectedCategory = if (selectedCategory == selected) null else selected
            }
        )

        val filteredItems = menuItems.filter { item ->
            (selectedCategory.isNullOrBlank() || item.category.equals(selectedCategory, ignoreCase = true)) &&
                    (searchPhrase.isBlank() || item.title.contains(searchPhrase, ignoreCase = true))
        }

        MenuItems(filteredItems)
        }
}



@Composable
fun MenuItems(menuItems: List<MenuItem>) {

    LazyColumn {
            items(items = menuItems, itemContent = {
            item -> MenuItemView(item)
        })
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
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop //Bild zuschneiden
        )
    }
}

@Composable
fun CategoryBar(
    categories: List<String>,
    selected: String?,
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selected

            Box(
                modifier = Modifier
                    .clickable { onCategorySelected(category) }
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = category,
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}