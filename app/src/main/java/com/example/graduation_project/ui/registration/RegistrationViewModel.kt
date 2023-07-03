package com.example.graduation_project.ui.registration

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


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: MainRepository
):ViewModel() {
    val registerResult: MutableLiveData<RegistrationResponse> = MutableLiveData()

    fun registerUser(registerRequest:RegistrationRequest) {
        viewModelScope.launch {
            try {
                val response = repository.register(registerRequest)
                if (response.code() == 201) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        registerResult.postValue(loginResponse)
                    } else {
                    }
                } else {
                    // Handle login failure
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

}