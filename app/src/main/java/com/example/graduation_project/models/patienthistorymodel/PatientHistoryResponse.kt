package com.example.graduation_project.models.patienthistorymodel


import com.google.gson.annotations.SerializedName

data class PatientHistoryResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val patientHistories: List<PatientHistory>
)