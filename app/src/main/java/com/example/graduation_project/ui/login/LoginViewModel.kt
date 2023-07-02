package com.example.graduation_project.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.api.BasicAuthorization
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.repository.MainRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo = MainRepository()
    private val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()

    fun loginUser(
        userName: String,
        password: String,
        onResult: (Result<LoginResponse?>) -> Unit
    ) {
        val basicAuthorization = BasicAuthorization(userName, password)
        val credentials = basicAuthorization.getCredentials()

        viewModelScope.launch {
            try {
                val response = userRepo.loginUser(credentials)
                if (response.code() == 200) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        loginResult.postValue(loginResponse)
                        onResult(Result.success(response.body()))
                    } else {
                        onResult(Result.failure(Exception("Login response is null")))
                    }
                } else {
                    onResult(Result.failure(Exception("Login failed")))
                }
            } catch (e: Exception) {
                onResult(Result.failure(e))
            }
        }
    }


}
