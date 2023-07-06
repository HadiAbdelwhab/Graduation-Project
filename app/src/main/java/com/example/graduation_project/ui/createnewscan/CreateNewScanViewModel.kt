package com.example.graduation_project.ui.createnewscan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanRequest
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanResponse
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateNewScanViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _createNewScanResult: MutableLiveData<CreateNewScanResponse> = MutableLiveData()
    val createNewScanResult: MutableLiveData<CreateNewScanResponse> get() = _createNewScanResult


    fun createNewScan(
        token: String, image: File, createNewScanRequest: CreateNewScanRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.createNewScan(
                    "Bearer $token", image, createNewScanRequest
                )
                if (response.isSuccessful){
                    val data = response.body()
                    _createNewScanResult.postValue(data)
                }


            }catch (e:Exception){

            }
        }

    }

}