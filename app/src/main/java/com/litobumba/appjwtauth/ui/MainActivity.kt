package com.litobumba.appjwtauth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litobumba.appjwtauth.BuildConfig
import com.litobumba.appjwtauth.ui.signin_screen.SignInScreen
import com.litobumba.appjwtauth.ui.signup_screen.SignUpScreen
import com.litobumba.appjwtauth.ui.theme.AppJWTAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppJWTAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "signin_screen"
                    ){
                        composable("signin_screen"){
                            SignInScreen(navigator = navController)
                        }
                        composable("signup_screen"){
                            SignUpScreen(navigator = navController)
                        }
                        composable("secret_screen"){
                            SecretScreen(navigator = navController)
                        }
                    }
                }
            }
        }
    }
}