// AppNavigation.kt
package com.example.eventeasy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventeasy.ui.screen.ResetPasswordScreen
import com.example.eventeasy.ui.screen.SignInScreen
import com.example.eventeasy.ui.screen.SignUpScreen
import com.example.eventeasy.ui.screen.SplashScreen
import com.example.eventeasy.ui.screen.VerificationScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController) }
        composable("signin") { SignInScreen(navController = navController) }
        composable("signup") { SignUpScreen(navController = navController) }
        composable("resetpassword") { ResetPasswordScreen(navController = navController) }
        composable("verification") { VerificationScreen(navController = navController) }
    }
}
