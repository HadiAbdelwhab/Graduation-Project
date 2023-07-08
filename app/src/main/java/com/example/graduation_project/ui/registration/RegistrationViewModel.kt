package com.example.graduation_project.ui.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.models.registermodel.RegistrationResponse
import com.example.graduation_project.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.graduation_project.models.registermodel.RegistrationRequest
import kotlinx.coroutines.Dispatchers


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _registerResult: MutableLiveData<RegistrationResponse> = MutableLiveData()
    val registerResult: MutableLiveData<RegistrationResponse> get() = _registerResult

    fun registerUser(registerRequest: RegistrationRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.register(registerRequest)
                Log.d("RegistrationViewModel", response.code().toString())
                if (response.code() == 201) {
                    val registerResponse = response.body()
                    _registerResult.postValue(registerResponse)
                } else {
                    // Handle login failure
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

}