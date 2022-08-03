package com.example.imdbtopmovies.repository

import androidx.lifecycle.MutableLiveData
import com.example.imdbtopmovies.api.ApiService
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTopMovies(id: Int) = apiService.getTopMovies(id)
    suspend fun getLastMovies() = apiService.getLastMovies()
    suspend fun getGenres() = apiService.getGenres()


}