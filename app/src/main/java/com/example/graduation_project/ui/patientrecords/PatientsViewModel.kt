package com.example.graduation_project.ui.patientrecords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(

    private val repository: MainRepository
    ) : ViewModel() {

    private val _getPatientsList: MutableLiveData<List<Patient>> = MutableLiveData()
    val getPatientsList: MutableLiveData<List<Patient>> get() = _getPatientsList

    fun getPatientsList(token:String) {
        viewModelScope.launch {
            try {
                val response =
                    repository.getPatientsList(token)
                val list = response.body()?.patients
                _getPatientsList.postValue(list)
            } catch (e: Exception) {

            }


        }
    }


}