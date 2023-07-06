package com.example.graduation_project.ui.createnewpatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientRequest
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientResponse
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CreateNewPatientViewModel @Inject constructor(
    private val repository: MainRepository
):ViewModel() {


    val createNewPatientResult:MutableLiveData<CreateNewPatientResponse> = MutableLiveData()


    fun createNewPatient(createNewPatientRequest: CreateNewPatientRequest, token:String){
        viewModelScope.launch {
            try {
                val response=repository.createNewPatient(token = "Bearer $token", createNewPatientRequest = createNewPatientRequest)

                if (response.code()==201){
                    val createNewPatientResponse=response.body()
                    if (createNewPatientResponse!=null){
                        createNewPatientResult.postValue(createNewPatientResponse)
                    }
                }

            }catch (e: Exception){

            }
        }
    }

}