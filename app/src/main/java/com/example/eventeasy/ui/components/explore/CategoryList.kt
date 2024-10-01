package com.example.eventeasy.ui.components.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventeasy.R

@Composable
fun CategoryList() {
    val categories = listOf(
        CategoryItem("Best", R.drawable.best),
        CategoryItem("Weddings", R.drawable.wedding),
        CategoryItem("Sweet 15", R.drawable.xv),
        CategoryItem("Birthday Parties", R.drawable.parties),
        // Añadir más categorías
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        categories.forEach { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = category.label,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = category.label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF747688)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

data class CategoryItem(val label: String, val icon: Int)
