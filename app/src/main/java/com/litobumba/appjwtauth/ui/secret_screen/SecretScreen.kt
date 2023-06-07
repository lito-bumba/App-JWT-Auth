package com.litobumba.appjwtauth.ui.secret_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SecretScreen(
    navigator: NavController,
    viewModel: SecretViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You're authenticated!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.logOut()
                navigator.navigate("auth_screen")
            },
        ) { Text(text = "Log out") }
    }
}