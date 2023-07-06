package com.example.graduation_project.api

import com.example.graduation_project.models.creditsmodel.CreditsResponse
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientRequest
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientResponse
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanRequest
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanResponse
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patienthistorymodel.PatientHistoryResponse
import com.example.graduation_project.models.patientsmodel.PatientResponse
import com.example.graduation_project.models.registermodel.RegistrationRequest
import com.example.graduation_project.models.registermodel.RegistrationResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File

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


    @GET("credits/refresh")
    suspend fun getCredits(
        @Header("Authorization") token: String
    ): Response<CreditsResponse>


    @Multipart
    @POST("api/scans/")
    suspend fun createNewScan(
        @Header("Authorization") token: String,
        @Part image: File,
        @Body createNewScanRequest: CreateNewScanRequest
    ):Response<CreateNewScanResponse>


}


