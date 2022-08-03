package com.example.imdbtopmovies.viewmodel

import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import com.example.imdbtopmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    val moviesList = MutableLiveData<TopMoviesResponse>()
    val loading = MutableLiveData<Boolean>()
    val emptyList = MutableLiveData<Boolean>()

    fun searchMovie(text: String) {
        viewModelScope.launch {
            val response = repository.searchMovie(text)
            loading.postValue(true)
            if (response.isSuccessful) {
                if (response.body()?.data!!.isNotEmpty()) {
                    emptyList.postValue(false)
                    moviesList.postValue(response.body())
                } else {
                    emptyList.postValue(true)
                }

            }
            loading.postValue(false)


        }

    }


}