package com.example.imdbtopmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbtopmovies.models.register.RegisterBody
import com.example.imdbtopmovies.models.register.RegisterResponse
import com.example.imdbtopmovies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {

    val registerUserResponse = MutableLiveData<RegisterResponse>()
    val loading = MutableLiveData<Boolean>()
    val TAG = "RegisterViewModel"
    fun registerUser(user: RegisterBody) {
        viewModelScope.launch {
            loading.postValue(true)
            val response = repository.registerUser(user)
            if (response.isSuccessful) {
                registerUserResponse.postValue(response.body())
            }
            Log.i(
                TAG,
                "registerUser:${response.code()}|errorBody:${response.errorBody()?.string()}|message: ${response.message()} | ${response.body()}"
            )
            loading.postValue(false)
        }
    }


}