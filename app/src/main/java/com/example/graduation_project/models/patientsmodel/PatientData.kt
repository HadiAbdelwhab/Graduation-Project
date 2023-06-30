package com.example.graduation_project.models.patientsmodel


import com.google.gson.annotations.SerializedName

data class PatientData(
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)