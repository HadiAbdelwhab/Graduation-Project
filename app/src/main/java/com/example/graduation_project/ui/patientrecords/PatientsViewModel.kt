package com.example.graduation_project.ui.patientrecords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.repository.MainRepository
import kotlinx.coroutines.launch

class PatientsViewModel(
    val app: Application,
    private val repository: MainRepository,
    private val sessionManager: SessionManager
) : AndroidViewModel(app) {

    private val _getPatientsList: MutableLiveData<List<Patient>> = MutableLiveData()
    val getPatientsList: MutableLiveData<List<Patient>> get() = _getPatientsList

    private fun getPatientsList() {
        viewModelScope.launch {
            try {
                val response =
                    repository.getPatientsList(token = "Bearer ${sessionManager.fetchAuthToken()}")
                val list = response.body()?.patients
                _getPatientsList.postValue(list)
            } catch (e: Exception) {

            }


        }
    }


}