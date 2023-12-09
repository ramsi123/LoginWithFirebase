package com.example.loginwithfirebase.navigation

import com.example.loginwithfirebase.util.Constants.HOME_SCREEN
import com.example.loginwithfirebase.util.Constants.SIGN_IN_SCREEN
import com.example.loginwithfirebase.util.Constants.SIGN_UP_SCREEN

sealed class Screen(val route: String) {
    object SignUp : Screen(SIGN_UP_SCREEN)
    object SignIn : Screen(SIGN_IN_SCREEN)
    object Home : Screen(HOME_SCREEN)
}
