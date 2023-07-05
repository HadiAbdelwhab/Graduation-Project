package com.example.graduation_project.ui.patienthistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.patienthistorymodel.PatientHistory
import com.example.graduation_project.models.patienthistorymodel.PatientHistoryResponse
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientHistoryViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _patientHistoryResult: MutableLiveData<List<PatientHistory>> = MutableLiveData()

    val patientHistoryResult: MutableLiveData<List<PatientHistory>> get() = _patientHistoryResult


    fun getPatientHistory(token: String, id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPatientHistory(
                    token = "Bearer $token", id = id
                )
                Log.d("PatientHistoryViewModel",response.code().toString())
                if (response.code() == 200) {

                    val patientHistoryResponse = response.body()
                    val list = patientHistoryResponse?.patientHistories
                    _patientHistoryResult.postValue(list)
                    Log.v(
                        "PatientHistoryViewModel",
                        patientHistoryResponse?.patientHistories.toString()
                    )
                }


            } catch (e: Exception) {

            }
        }


    }

}