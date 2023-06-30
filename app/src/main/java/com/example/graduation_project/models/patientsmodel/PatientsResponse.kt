package com.example.graduation_project.models.patientsmodel


import com.google.gson.annotations.SerializedName

data class PatientsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val patientData: List<PatientData>
)