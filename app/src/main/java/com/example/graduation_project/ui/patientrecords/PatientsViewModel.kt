package com.example.graduation_project.ui.patientrecords

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.creditsmodel.CreditsResponse
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(

    private val repository: MainRepository
) : ViewModel() {

    private val _getPatientsList: MutableLiveData<List<Patient>> = MutableLiveData()
    val getPatientsList: MutableLiveData<List<Patient>> get() = _getPatientsList

    private val _creditsLiveData: MutableLiveData<CreditsResponse> = MutableLiveData()
    val creditLiveData: MutableLiveData<CreditsResponse> get() = _creditsLiveData
    fun getPatientsList(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPatientsList("Bearer $token")
                Log.d("PatientsViewModel", "Bearer $token")
                if (response.isSuccessful) {
                    val patientResponse = response.body()
                    val list = patientResponse?.patients
                    _getPatientsList.postValue(list)
                } else {
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    fun deletePatient(id: Int, token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.deletePatient(id, "Bearer $token")

            } catch (e: Exception) {
                // Handle exceptions that occurred during the deletion process
            }
        }
    }

    fun getCredits(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCredits("Bearer $token")
                if (response.isSuccessful) {
                    val creditsResponse = response.body()
                    _creditsLiveData.postValue(creditsResponse)
                }
            } catch (e: Exception) {

            }
        }
    }


}