package com.litobumba.appjwtauth.ui.signup_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litobumba.appjwtauth.auth.AuthRepository
import com.litobumba.appjwtauth.auth.AuthResult
import com.litobumba.appjwtauth.ui.signin_screen.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(SignInState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    fun onUsernameChanged(value: String){
        state = state.copy(username = value)
    }
    fun onPasswordChanged(value: String){
        state = state.copy(password = value)
    }

    fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                username = state.username,
                password = state.password
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}