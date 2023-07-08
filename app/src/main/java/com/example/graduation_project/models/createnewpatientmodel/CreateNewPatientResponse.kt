package com.example.graduation_project.models.createnewpatientmodel

import com.google.gson.annotations.SerializedName

data class CreateNewPatientResponse (
    val id:Int,
    @SerializedName("first_name")
    val firstName:String,
    @SerializedName("last_name")
    val lastName:String,
    val gender:Int,
    @SerializedName("birth_date")
    val date:String

)