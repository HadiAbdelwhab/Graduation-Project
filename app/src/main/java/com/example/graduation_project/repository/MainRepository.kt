package com.example.graduation_project.repository

import com.example.graduation_project.api.RetrofitInstance
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.models.patientsmodel.PatientResponse
import retrofit2.Response

class MainRepository {
    private val apiService = RetrofitInstance.api

    suspend fun loginUser(credentials: String): Response<LoginResponse> {
        return apiService.login(credentials)
    }

    suspend fun getPatientsList(token: String): Response<PatientResponse> {
        return apiService.getPatientList(token)
    }

    //suspend fun register()
}