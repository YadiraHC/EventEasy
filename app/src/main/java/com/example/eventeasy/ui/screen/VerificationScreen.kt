package com.example.eventeasy.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.eventeasy.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }
    var timeRemaining by remember { mutableStateOf(20) }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Temporizador para reenviar el código
    LaunchedEffect(timeRemaining) {
        while (timeRemaining > 0) {
            delay(1000L)
            timeRemaining--
        }
    }

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
            text = "Verification",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF120D26)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "We've sent you the verification code on abc@email.com",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF747688)
        )

        Spacer(modifier = Modifier.height(38.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = code1,
                onValueChange = {
                    if (it.length <= 1) {
                        code1 = it
                        if (it.isNotEmpty()) {
                            focusRequester2.requestFocus()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequester1),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF5669FF),
                    unfocusedBorderColor = Color(0xFF747688),
                )
            )
            OutlinedTextField(
                value = code2,
                onValueChange = {
                    if (it.length <= 1) {
                        code2 = it
                        if (it.isNotEmpty()) {
                            focusRequester3.requestFocus()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequester2),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF5669FF),
                    unfocusedBorderColor = Color(0xFF747688),
                )
            )
            OutlinedTextField(
                value = code3,
                onValueChange = {
                    if (it.length <= 1) {
                        code3 = it
                        if (it.isNotEmpty()) {
                            focusRequester4.requestFocus()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequester3),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF5669FF),
                    unfocusedBorderColor = Color(0xFF747688),
                )
            )
            OutlinedTextField(
                value = code4,
                onValueChange = {
                    if (it.length <= 1) {
                        code4 = it
                        if (it.isNotEmpty()) {
                            focusManager.clearFocus()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequester4),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF5669FF),
                    unfocusedBorderColor = Color(0xFF747688),
                )
            )
        }

        Spacer(modifier = Modifier.height(38.dp))


        Button(
            onClick = {

                navController.navigate("resetpassword")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5669FF))
        ) {
            Text(text = "Continue", color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(28.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Re-send code in ", color = Color(0xFF747688), style = MaterialTheme.typography.bodyMedium)
            Text(text = "0:${String.format("%02d", timeRemaining)}", color = Color(0xFF5669FF), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
