package com.example.eventeasy.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LanguageDropMenu(onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("en" to "English", "es" to "Español", "fr" to "Français")
    var selectedLanguage by remember { mutableStateOf("English") }

    Box(modifier = Modifier.padding(16.dp)) {
        // Botón que despliega el menú
        Button(onClick = { expanded = true }) {
            Text(text = selectedLanguage)
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Cada opción del menú
            languages.forEach { (code, label) ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedLanguage = label
                        expanded = false
                        onLanguageSelected(code)
                    }
                )
            }
        }
    }
}
