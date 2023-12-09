package com.example.loginwithfirebase

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginwithfirebase.navigation.Screen
import com.example.loginwithfirebase.ui.screen.home.HomeScreen
import com.example.loginwithfirebase.ui.screen.signin.SignInScreen
import com.example.loginwithfirebase.ui.screen.signup.SignUpScreen
import com.example.loginwithfirebase.util.Constants.SIGN_IN_SCREEN

@Composable
fun FirebaseLoginApp(
    activity: ComponentActivity
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SIGN_IN_SCREEN
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(navController = navController, activity = activity)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController, activity = activity)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}