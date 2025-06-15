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
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    //Caro: Korrektur: HttpClient(Android), Sonst super!
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Caro: viewModelScope.launch(Dispatchers.IO) {
        //Caro: Siehe: https://developer.android.com/kotlin/coroutines?hl=de
        CoroutineScope(Dispatchers.IO).launch {
            //Caro: Abschnitt soweit ok, du musst nur aufpassen, das wird aktuell jedes Mal gemacht.
            //Caro: Wahrscheinlich wäre eine Abfrage, ob sich bereits etwas in der Datenbank befindet sinnvoll
            //Caro: Prüfe erstmal, ob es zu Mehrfacheinträgen in deiner Datenbank kommt
            try {
                //Gut
                // Verwenden Sie den httpClient, um die JSON-Daten abzurufen, Daten vom Netzwerk holen
                val response: MenuNetwork =
                    httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                        .body<MenuNetwork>()
                //Gut
                // 2. Netzwerk-Daten in Room-Entities umwandeln
                val items = response.menu.map { it.toMenuItem() }
                //Caro: Wie in Database kommentiert bitte hier den Code von dort einfügen
                //in Room-Datenbank speichern
                val db = AppDatabase.getDatabase(applicationContext)
                val menuItemDao = db.menuItemDao()

                // Daten speichern
                //Caro: Das druckt die Daten nur ->
                //Caro: response.menu.forEach( menuItem -> menuItemDao.saveMenuItem(menuItem))
                response.menu.forEach { menuItem ->
                    println("Title: ${menuItem.title}, Price: ${menuItem.price}")
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