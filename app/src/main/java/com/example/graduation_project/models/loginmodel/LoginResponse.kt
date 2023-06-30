package com.example.graduation_project.models.loginmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<ResultItem>
): Serializable
data class ResultItem(
    val id: Int,
    val eye: Int,
    val result: Int,
    val notes: String,
    val image: String,
    val mask: String,
    val ratio: Int,
    val patient: Int
)