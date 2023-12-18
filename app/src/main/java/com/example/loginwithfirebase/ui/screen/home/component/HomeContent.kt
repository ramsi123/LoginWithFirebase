package com.example.loginwithfirebase.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginwithfirebase.components.CircularProgressBar
import com.example.loginwithfirebase.ui.theme.ghostWhite
import com.example.loginwithfirebase.util.formatSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    availableMemory: Long,
    totalMemory: Long,
    storageProgressBar: Float,
    onSignOut: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(ghostWhite)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Internal Available ${formatSize(availableMemory)}")
            Text(text = "Internal Total ${formatSize(totalMemory)}")
            //CustomProgressBar(storageProgress = storageProgressBar)
            Spacer(modifier = modifier.height(20.dp))
            CircularProgressBar(
                percentage = storageProgressBar,
                availableMemory = formatSize(availableMemory) ?: "0",
                totalMemory = " of ${formatSize(totalMemory)} Used",
                number = 100
            )
            Spacer(modifier = modifier.height(20.dp))
            Button(
                onClick = {
                    onSignOut()
                }
            ) {
                Text(text = "Sign Out")
            }
        }
    }
}