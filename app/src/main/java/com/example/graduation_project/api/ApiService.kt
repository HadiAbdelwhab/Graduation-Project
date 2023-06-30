package com.example.graduation_project.api

import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patientsmodel.PatientsResponse
import com.example.graduation_project.models.registermodel.RegistrationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Header("Authorization")credentials:String): Response<LoginResponse>

    @POST("api/signup")
    suspend fun register(@Header("Authorization")credentials: String):Response<RegistrationResponse>

    /*@GET("api/patient-history/{$id}")
    suspend fun getPatientHistory*/

    @GET("api/patients/")
    suspend fun getPatients():Response<PatientsResponse>

}