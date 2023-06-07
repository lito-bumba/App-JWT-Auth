package com.litobumba.appjwtauth.ui.signin_screen

data class SignInState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = ""
)
