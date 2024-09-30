package com.example.eventeasy.ui.screen

import AuthViewModel
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eventeasy.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val splashImage: Painter = painterResource(id = R.drawable.splash_logo)
        Image(painter = splashImage, contentDescription = "EventEasy Logo")

        // Verificar si ya está autenticado
        authViewModel.checkIfAuthenticated()

        // Simulamos un retraso de 2 segundos y verificamos si ya está autenticado
        LaunchedEffect(Unit) {
            delay(2000)

            if (authViewModel.isAuthenticated) {
                // Si ya está autenticado, navegar directamente a la pantalla de inicio
                navController.navigate("explore") {
                    popUpTo("splash") { inclusive = true }
                }
            } else {
                // Si no está autenticado, navegar a la pantalla de SignIn
                navController.navigate("signin") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }
}

