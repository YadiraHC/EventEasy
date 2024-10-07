package com.example.eventeasy.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.stringResource
import com.example.eventeasy.R
import com.example.eventeasy.viewmodel.AuthViewModel

@Composable
fun ResetPasswordScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                tint = Color(0xFF120D26)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.reset_password),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF120D26),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.sign_in_email)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))

        Button(
            onClick = {
                // Lógica para enviar el correo de recuperación
                navController.navigate("verification")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 30.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5669FF))
        ) {
            Text(text = stringResource(id = R.string.reset), color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(38.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.remember_password), color = Color(0xFF120D26))
            TextButton(onClick = {
                navController.navigate("signin")
            }) {
                Text(text = stringResource(id = R.string.sign_in_here), color = Color(0xFF5669FF))
            }
        }
    }
}
