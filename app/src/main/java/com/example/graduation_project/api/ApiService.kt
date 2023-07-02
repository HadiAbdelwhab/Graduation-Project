package com.example.graduation_project.api

import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patientsmodel.PatientResponse
import com.example.graduation_project.models.registermodel.RegistrationResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/login/")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/signup")
    suspend fun register(
        @Header("Authorization") credentials: String
    ): Response<RegistrationResponse>


    @GET("api/patients")
    suspend fun getPatientList(
        @Header("Authorization") token: String
    ): Response<PatientResponse>

}


/*@GET("api/patient-history/{$id}")
suspend fun getPatientHistory*/

/*@GET("api/patients/")
suspend fun getPatients():Response<PatientsResponse>*/

