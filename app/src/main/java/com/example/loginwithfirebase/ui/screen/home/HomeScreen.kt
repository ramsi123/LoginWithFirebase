package com.example.loginwithfirebase.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.loginwithfirebase.navigation.Screen
import com.example.loginwithfirebase.ui.screen.home.component.HomeContent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val auth = Firebase.auth

    HomeContent(
        modifier = modifier,
        onSignOut = {
            auth.signOut()
            navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.SignIn.route) {
                    inclusive = true
                }
            }
        }
    )
}