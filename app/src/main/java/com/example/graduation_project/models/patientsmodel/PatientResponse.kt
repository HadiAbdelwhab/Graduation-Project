package com.example.graduation_project.models.patientsmodel


import com.google.gson.annotations.SerializedName

data class PatientResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val patients: List<Patient>
)