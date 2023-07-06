package com.example.graduation_project.models.createnewpatientmodel

data class CreateNewPatientRequest(
    val firstName:String,
    val lastName:String,
    val gender:Int,
    val date:String
)