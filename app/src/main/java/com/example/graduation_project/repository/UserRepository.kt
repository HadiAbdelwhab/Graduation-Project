package com.example.graduation_project.repository

import com.example.graduation_project.api.RetrofitInstance
import com.example.graduation_project.models.loginmodel.LoginResponse
import retrofit2.Response

class UserRepository {
    private val apiService = RetrofitInstance.api

    suspend fun loginUser(credentials: String): Response<LoginResponse> {
        return apiService.login(credentials)
    }

    //suspend fun register()
}