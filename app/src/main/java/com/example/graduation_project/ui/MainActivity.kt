package com.example.graduation_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_project.R
import com.example.graduation_project.ui.createnewpatient.CreateNewPatientViewModel
import com.example.graduation_project.ui.createnewscan.CreateNewScanViewModel
import com.example.graduation_project.ui.login.LoginViewModel
import com.example.graduation_project.ui.patienthistory.PatientHistoryViewModel
import com.example.graduation_project.ui.patientrecords.PatientsViewModel
import com.example.graduation_project.ui.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var createNewPatientViewModel: CreateNewPatientViewModel
    lateinit var registerViewModel: RegistrationViewModel
    lateinit var loginViewModel: LoginViewModel
    lateinit var patientsViewModel: PatientsViewModel
    lateinit var patientHistoryViewModel: PatientHistoryViewModel
    lateinit var createNewScanViewModel: CreateNewScanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        patientsViewModel = ViewModelProvider(this)[PatientsViewModel::class.java]
        registerViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        patientHistoryViewModel = ViewModelProvider(this)[PatientHistoryViewModel::class.java]
        createNewPatientViewModel = ViewModelProvider(this)[CreateNewPatientViewModel::class.java]
        createNewScanViewModel = ViewModelProvider(this)[CreateNewScanViewModel::class.java]
    }
}