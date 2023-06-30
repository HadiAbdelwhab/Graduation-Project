package com.example.graduation_project.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.api.BasicAuthorization
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.repository.UserRepository
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo = UserRepository()
    private val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()

    suspend fun loginUser(userName: String, password: String): Result<LoginResponse?> {
        val basicAuthorization = BasicAuthorization(userName, password)
        val credentials = basicAuthorization.getCredentials()

        return withContext(viewModelScope.coroutineContext) {
            try {
                val response = userRepo.loginUser(credentials)
                if (response?.code() == 200) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Login failed"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}
