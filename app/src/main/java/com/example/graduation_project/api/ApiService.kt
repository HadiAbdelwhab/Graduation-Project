package com.example.graduation_project.api

import com.example.graduation_project.models.loginmodel.createnewpatientmodel.CreateNewPatientRequest
import com.example.graduation_project.models.loginmodel.createnewpatientmodel.CreateNewPatientResponse
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patienthistorymodel.PatientHistoryResponse
import com.example.graduation_project.models.patientsmodel.PatientResponse
import com.example.graduation_project.models.registermodel.RegistrationRequest
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
        @Body request: RegistrationRequest
    ): Response<RegistrationResponse>


    @GET("api/patients/")
    suspend fun getPatientList(
        @Header("Authorization") token: String
    ): Response<PatientResponse>

    @POST("api/patients/")
    suspend fun createNewPatient(
        @Header("Authorization") token: String,
        @Body createNewPatientRequest: CreateNewPatientRequest
    ): Response<CreateNewPatientResponse>


    @DELETE("/api/patients/{id}/")
    suspend fun deletePatient(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    )


    @GET("api/patient-history/{id}")
    suspend fun getPatientHistory(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<PatientHistoryResponse>

}


