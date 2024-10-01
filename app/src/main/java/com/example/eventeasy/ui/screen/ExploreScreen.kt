package com.example.eventeasy.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventeasy.ui.components.explore.SearchBar
import com.example.eventeasy.ui.components.explore.CategoryList

@Composable
fun ExploreScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SearchBar()

        CategoryList()
    }
}
