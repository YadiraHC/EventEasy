package com.example.eventeasy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventeasy.R

@Composable
fun LanguageDropMenu(selectedLanguage: String, onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val languages = listOf(
        "select" to Pair(stringResource(id = R.string.language), R.drawable.language),
        "en" to Pair("English", R.drawable.usa),
        "es" to Pair("Español", R.drawable.mexico)
    )

    Box(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.clickable { expanded = true }) {
            Image(
                painter = painterResource(id = languages.first { it.first == selectedLanguage }.second.second),
                contentDescription = "Language Flag",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = languages.first { it.first == selectedLanguage }.second.first, color = Color.Black)
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { (code, pair) ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Image(
                                painter = painterResource(id = pair.second),
                                contentDescription = "Flag",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = pair.first)
                        }
                    },
                    onClick = {
                        if (code != "select") {
                            expanded = false
                            onLanguageSelected(code)
                        }
                    }
                )
            }
        }
    }
}
