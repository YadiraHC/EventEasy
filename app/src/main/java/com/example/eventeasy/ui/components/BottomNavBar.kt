package com.example.eventeasy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem("explore", "Explore", Icons.Default.Explore),
        BottomNavItem("favorites", "Favorites", Icons.Default.Favorite),
        BottomNavItem("events", "Events", Icons.Default.CalendarToday),
        BottomNavItem("messages", "Messages", Icons.Default.Email),
        BottomNavItem("profile", "Profile", Icons.Default.Person)
    )


    Surface(
        color = Color.White,
        modifier = Modifier
            .height(80.dp)
    ) {
        NavigationBar(
            modifier = Modifier.background(Color.White),
            containerColor = Color.White
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (isSelected) Color(0xFF5669FF) else Color(0xFFB0B0B0)
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = item.label,
                                color = if (isSelected) Color(0xFF5669FF) else Color(0xFFB0B0B0),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

data class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
