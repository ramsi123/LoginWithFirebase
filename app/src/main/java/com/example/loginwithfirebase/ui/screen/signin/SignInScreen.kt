package com.example.loginwithfirebase.ui.screen.signin

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.loginwithfirebase.navigation.Screen
import com.example.loginwithfirebase.ui.screen.signin.component.SignInContent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    activity: ComponentActivity
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = Firebase.auth

    LaunchedEffect(key1 = true) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navController.navigate(Screen.Home.route)
        }
    }

    SignInContent(
        modifier = modifier,
        email = email,
        onEmailValueChange = {
            email = it
        },
        password = password,
        onPasswordValueChange = {
            password = it
        },
        onSignIn = { email, password ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.SignIn.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, task.exception?.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        },
        navigateToSignUpScreen = {
            navController.navigate(Screen.SignUp.route)
        }
    )
}