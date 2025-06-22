package com.littlelemon.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.littlelemon.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log


class MainActivity: ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DEBUG", "MainActivity started")

        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
              try {
                // httpClient verwenden, um die JSON-Daten abzurufen, Daten vom Netzwerk holen
                val response: MenuNetwork =
                    client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                        .body<MenuNetwork>()
                Log.d("DEBUG", "Network response size: ${response.menu.size}")
                // 2. Netzwerk-Daten in Room-Entities umwandeln
                  Log.d("DEBUG", "JSON Response: ${response.menu}")

                  val items = response.menu.map { it.toMenuItem() }
                Log.d("DEBUG", "Items mapped: ${items.size}")
                Log.d("Network", "Fetched ${items.size} items from API")
                //Datenbankinstanz abrufen
                val db = AppDatabase.getDatabase(applicationContext)
                //AF: val db = Room.databaseBuilder(
                //AF:     applicationContext.applicationContext,
                //AF:     AppDatabase::class.java,
                //AF:     "menu.db"
                //AF: ).build()

                val menuItemDao = db.menuItemDao()
                // Daten speichern
                items.forEach { menuItem ->
                    Log.d("DEBUG", "Saving item: ${menuItem.title}")
                    menuItemDao.saveMenuItem(menuItem)
                    Log.d("MainActivity", "Save ${items.size} Einträge in DB")
                    }
                val itemsInDb = menuItemDao.getAllMenuItemsNow()
                Log.d("MainActivity", "→ In DB nach Save: ${itemsInDb.size} Einträge")


            } catch (e: Exception) {
                // Fehlerbehandlung
                e.printStackTrace()
            }
        }
    //UI anzeigen
        setContent {
            LittleLemonTheme {

                Scaffold() {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        MyNavigation()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        client.close()
    }
}

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    navigation(navController)
}

@Preview(showBackground = true)
@Composable
fun MyNavigationPreview() {
    LittleLemonTheme {
        MyNavigation()
    }
}