package com.example.eventeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventeasy.ui.components.BottomNavBar
import com.example.eventeasy.ui.navigation.AppNavigation
import com.example.eventeasy.ui.theme.EventEasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventEasyTheme {
                val navController = rememberNavController()

                Surface(color = Color.White) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(1f)) {
                            AppNavigation(navController = navController)
                        }
                        // Mostrar el BottomNavBar solo en las pantallas adecuadas
                        val currentRoute = getCurrentRoute(navController)
                        if (currentRoute in listOf("explore", "favorites", "events", "messages", "profile")) {
                            BottomNavBar(navController = navController)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun getCurrentRoute(navController: NavController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }
}
