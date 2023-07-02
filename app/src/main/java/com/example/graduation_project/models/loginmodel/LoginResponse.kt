package com.example.graduation_project.models.loginmodel

data class LoginResponse(
    val refresh:String,
    val access:String,
    val credits:Int,
    val id:Int
)