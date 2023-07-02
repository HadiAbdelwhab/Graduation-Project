package com.example.graduation_project.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.api.BasicAuthorization
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.repository.MainRepository
import kotlinx.coroutines.launch

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.graduation_project.models.loginmodel.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val userRepo: MainRepository
) : ViewModel() {

    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("your_app_shared_prefs", Context.MODE_PRIVATE)

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = userRepo.loginUser(loginRequest)
                if (response.code() == 200) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        saveToken(loginResponse.access)
                        loginResult.postValue(loginResponse)
                        // Handle successful login, navigate to the dashboard, etc.
                    } else {
                        // Handle null login response
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
        sharedPreferences.edit().putString("token", token).apply()
    }
}



