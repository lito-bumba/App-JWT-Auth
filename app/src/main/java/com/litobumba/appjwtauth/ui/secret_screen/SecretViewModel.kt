package com.litobumba.appjwtauth.ui.secret_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litobumba.appjwtauth.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecretViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            repository.logOut()
        }
    }
}