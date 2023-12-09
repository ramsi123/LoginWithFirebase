package com.example.loginwithfirebase.ui.screen.signup

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.loginwithfirebase.navigation.Screen
import com.example.loginwithfirebase.ui.screen.signup.component.SignUpContent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    activity: ComponentActivity
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = Firebase.auth

    SignUpContent(
        modifier = modifier,
        email = email,
        onEmailValueChange = {
            email = it
        },
        password = password,
        onPasswordValueChange = {
            password = it
        },
        navigateBack = {
            navController.navigateUp()
        },
        onSignUp = { email, password ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.SignIn.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, task.exception?.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    )
}