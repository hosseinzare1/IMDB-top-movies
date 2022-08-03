package com.example.imdbtopmovies.repository

import com.example.imdbtopmovies.api.ApiService
import com.example.imdbtopmovies.models.register.RegisterBody
import javax.inject.Inject


class RegisterRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun registerUser(registerBody: RegisterBody) = apiService.registerUser(registerBody)

}