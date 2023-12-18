package com.example.loginwithfirebase.ui.screen.home

import android.os.Environment
import android.os.StatFs
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.loginwithfirebase.navigation.Screen
import com.example.loginwithfirebase.ui.screen.home.component.HomeContent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val auth = Firebase.auth
    var availableMemory by remember { mutableLongStateOf(0) }
    var totalMemory by remember { mutableLongStateOf(0) }
    var storageProgressBar by remember { mutableFloatStateOf(0f) }

    // get available and total memory
    LaunchedEffect(key1 = true) {
        val iPath: File = Environment.getDataDirectory()
        val iStat = StatFs(iPath.path)
        val iBlockSize = iStat.blockSizeLong
        val iAvailableBlocks = iStat.availableBlocksLong
        val iTotalBlocks = iStat.blockCountLong
        availableMemory = iAvailableBlocks * iBlockSize
        totalMemory = iTotalBlocks * iBlockSize

        val available = (iAvailableBlocks * iBlockSize).toFloat()
        val total = (iTotalBlocks * iBlockSize).toFloat()
        val divide = (-(available / total)) + 1
        storageProgressBar = divide
    }

    HomeContent(
        modifier = modifier,
        availableMemory = availableMemory,
        totalMemory = totalMemory,
        storageProgressBar = storageProgressBar,
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