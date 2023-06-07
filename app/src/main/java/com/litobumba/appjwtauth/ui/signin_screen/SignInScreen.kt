package com.litobumba.appjwtauth.ui.signin_screen

import PasswordTextField
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.litobumba.appjwtauth.auth.AuthResult
import kotlinx.coroutines.flow.collect

@Composable
fun SignInScreen(
    navigator: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResult.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navigator.navigate("secret_screen")
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(context, "You're not authorized", Toast.LENGTH_LONG).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "An unknown error occorred", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(64.dp))
        TextField(
            value = state.username,
            onValueChange = { viewModel.onUsernameChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Username") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Username Icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.signIn() }
        ) { Text(text = "Sign in") }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "I don't have an account yet",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1.copy(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable {
                navigator.navigate("signup_screen")
            }
        )
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }
    }
}