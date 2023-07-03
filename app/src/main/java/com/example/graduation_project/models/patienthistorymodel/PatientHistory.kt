package com.example.graduation_project.models.patienthistorymodel


import com.google.gson.annotations.SerializedName

data class PatientHistory(
    @SerializedName("eye")
    val eye: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("mask")
    val mask: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("patient")
    val patient: Int,
    @SerializedName("ratio")
    val ratio: Int,
    @SerializedName("result")
    val result: Int
)