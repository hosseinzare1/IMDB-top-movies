package com.example.imdbtopmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbtopmovies.models.home.GenresResponse
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import com.example.imdbtopmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    val topMoviesList = MutableLiveData<TopMoviesResponse>()
    val genresList = MutableLiveData<GenresResponse>()
    val lastMoviesList = MutableLiveData<TopMoviesResponse>()

    val loading = MutableLiveData<Boolean>()
    fun getTopMovies(id: Int) {
        viewModelScope.launch {
            val response = repository.getTopMovies(id)
            if (response.isSuccessful) {
                topMoviesList.postValue(response.body())
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            val response = repository.getGenres()
            if (response.isSuccessful) {
                genresList.postValue(response.body())
            }
        }
    }

    fun getLastMovies() = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getLastMovies()
        if (response.isSuccessful) {
            lastMoviesList.postValue(response.body())
        }
        loading.postValue(false)
    }

}