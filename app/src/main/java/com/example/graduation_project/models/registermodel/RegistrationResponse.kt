package com.example.graduation_project.models.registermodel

data class RegistrationResponse(
    val id: Int,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password1: String,
    val password2: String
)
