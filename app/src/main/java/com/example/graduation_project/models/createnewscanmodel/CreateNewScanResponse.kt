package com.example.graduation_project.models.createnewscanmodel

data class CreateNewScanResponse(

    val id: Int,
    val eye: Int,
    val result: Int,
    val notes: String,
    val image: String,
    val mask: String,
    val ratio: Int,
    val patient: Int


)