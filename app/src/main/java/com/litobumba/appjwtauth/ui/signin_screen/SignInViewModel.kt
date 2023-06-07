package com.litobumba.appjwtauth.ui.signin_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litobumba.appjwtauth.auth.AuthRepository
import com.litobumba.appjwtauth.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(SignInState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    init {
        getAuth()
        authenticate()
    }

    fun onUsernameChanged(value: String){
        state = state.copy(username = value)
    }

    fun onPasswordChanged(value: String){
        state = state.copy(password = value)
    }

    fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                username = state.username,
                password = state.password
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun getAuth() {
        viewModelScope.launch {
            val authData = repository.getAuthData()
            state = state.copy(
                username = authData.username,
                password = authData.password
            )
        }
    }
}