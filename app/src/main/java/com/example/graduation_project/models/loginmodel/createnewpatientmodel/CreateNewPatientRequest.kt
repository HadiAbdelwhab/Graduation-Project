package com.example.graduation_project.models.loginmodel.createnewpatientmodel

data class CreateNewPatientRequest(
    val firstName:String,
    val lastName:String,
    val gender:Int,
    val date:String
)