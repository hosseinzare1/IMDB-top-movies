package com.example.imdbtopmovies.repository

import com.example.imdbtopmovies.api.ApiService
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun searchMovie(text: String): Response<TopMoviesResponse> =
        apiService.searchMovies(text)

}