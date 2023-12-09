package com.example.loginwithfirebase.ui.screen.signin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    email: String,
    onEmailValueChange: (newValue: String) -> Unit,
    password: String,
    onPasswordValueChange: (newValue: String) -> Unit,
    onSignIn: (String, String) -> Unit,
    navigateToSignUpScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarSignIn()
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "Email",
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        onEmailValueChange(it)
                    }
                )
                Spacer(modifier = modifier.height(15.dp))
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "Password",
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        onPasswordValueChange(it)
                    }
                )
                Spacer(modifier = modifier.height(25.dp))
                Button(
                    onClick = {
                        onSignIn(email, password)
                    }
                ) {
                    Text(text = "Sign In")
                }
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    modifier = modifier.clickable {
                        navigateToSignUpScreen()
                    },
                    text = "Sign Up",
                    color = Color.Blue
                )
            }
        }
    }
}