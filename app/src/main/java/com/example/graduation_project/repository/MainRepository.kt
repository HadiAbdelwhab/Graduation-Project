package com.example.graduation_project.repository

import com.example.graduation_project.api.ApiService
import com.example.graduation_project.models.creditsmodel.CreditsResponse
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientRequest
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientResponse
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanRequest
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.loginmodel.LoginResponse
import com.example.graduation_project.models.patienthistorymodel.PatientHistoryResponse
import com.example.graduation_project.models.patientsmodel.PatientResponse
import com.example.graduation_project.models.registermodel.RegistrationRequest
import com.example.graduation_project.models.registermodel.RegistrationResponse
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {


    suspend fun loginUser(
        loginRequest: LoginRequest
    ): Response<LoginResponse> =
        apiService.login(loginRequest)

    suspend fun register(
        registerRequest: RegistrationRequest
    ): Response<RegistrationResponse> =
        apiService.register(registerRequest)


    suspend fun getPatientsList(
        token: String
    ): Response<PatientResponse> =
        apiService.getPatientList(token)

    suspend fun getPatientHistory(
        token: String,
        id: Int
    ): Response<PatientHistoryResponse> =
        apiService.getPatientHistory(
            token = token, id = id
        )

    suspend fun createNewPatient(
        createNewPatientRequest: CreateNewPatientRequest,
        token: String
    ): Response<CreateNewPatientResponse> =
        apiService.createNewPatient(
            token = token, createNewPatientRequest = createNewPatientRequest
        )

    suspend fun deletePatient(
        id: Int,
        token: String
    ) = apiService.deletePatient(token, id)

    suspend fun getCredits(
        token: String
    ): Response<CreditsResponse> =
        apiService.getCredits(token)


    suspend fun createNewScan(
        token: String,
        imageFile:File,
        createNewScanRequest: CreateNewScanRequest
    ) = apiService.createNewScan(token,imageFile,createNewScanRequest)
}