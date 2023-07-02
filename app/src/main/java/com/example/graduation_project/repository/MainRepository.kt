package com.example.graduation_project.repository

import com.example.graduation_project.api.ApiService
import com.example.graduation_project.api.RetrofitInstance
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.models.patientsmodel.PatientResponse
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService:ApiService
) {


    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }

    suspend fun getPatientsList(token: String): Response<PatientResponse> {
        return apiService.getPatientList(token)
    }

    //suspend fun register()
}