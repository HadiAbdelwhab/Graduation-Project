package com.example.graduation_project.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.repository.MainRepository
import kotlinx.coroutines.launch

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.util.Constants.Companion.SHA_PRF_KEY
import com.example.graduation_project.util.Constants.Companion.TOKEN_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val userRepo: MainRepository
) : ViewModel() {

    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(SHA_PRF_KEY, Context.MODE_PRIVATE)

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = userRepo.loginUser(loginRequest)
                if (response.code() == 200) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        saveToken(loginResponse.access)
                        loginResult.postValue(loginResponse)
                    } else {
                    }
                } else {
                    // Handle login failure
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }
}



