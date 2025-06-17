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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.littlelemon.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity(Android: HttpClientEngine) : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {json(contentType = ContentType("text", "plain"))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Verwenden Sie den httpClient, um die JSON-Daten abzurufen, Daten vom Netzwerk holen
                val response: MenuNetwork =
                    httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                        .body<MenuNetwork>()
                // 2. Netzwerk-Daten in Room-Entities umwandeln
                val items = response.menu.map { it.toMenuItem() }
                //Datenbankinstanz abrufen
               // val db = AppDatabase.getDatabase(applicationContext)
                val db = Room.databaseBuilder(
                    applicationContext.applicationContext,
                    AppDatabase::class.java,
                    "menu.db"
                ).build()
                val menuItemDao = db.menuItemDao()
                // Daten speichern
                items.forEach { menuItem ->
                    //println("Title: ${menuItem.title}, Price: ${menuItem.price}")
                    menuItemDao.saveMenuItem(menuItem)
                }

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
        httpClient.close()
    }
}

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    navigation(navController)
}

@Preview(showBackground = true)
@Composable
fun myNavigationPreview() {
    LittleLemonTheme {
        MyNavigation()
    }
}