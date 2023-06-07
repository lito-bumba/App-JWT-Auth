package com.litobumba.appjwtauth.ui.signup_screen

data class SignUpState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = ""
)
