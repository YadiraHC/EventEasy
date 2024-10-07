package com.example.eventeasy

import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventeasy.ui.components.BottomNavBar
import com.example.eventeasy.ui.navigation.AppNavigation
import com.example.eventeasy.ui.theme.EventEasyTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventEasyTheme {
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Box(modifier = Modifier.weight(1f)) {
                            AppNavigation(navController = navController)
                        }
                        val currentRoute = getCurrentRoute(navController)
                        if (currentRoute in listOf("explore", "favorites", "events", "messages", "profile")) {
                            BottomNavBar(navController = navController)
                        }
                    }
                }
            }
        }
    }

    fun changeLanguage(localeCode: String) {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        recreate() // Reinicia la actividad para aplicar los cambios
    }

    @Composable
    private fun getCurrentRoute(navController: NavController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

    fun saveLanguagePreference(context: Context, languageCode: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("LANGUAGE_CODE", languageCode)
        editor.apply()
    }

    fun getLanguagePreference(context: Context): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("LANGUAGE_CODE", "es") ?: "es" // "es" es el valor predeterminado
    }
}
