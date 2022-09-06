package com.example.imdbtopmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbtopmovies.db.MovieEntity
import com.example.imdbtopmovies.models.details.MovieDetailsResponse
import com.example.imdbtopmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    val detail = MutableLiveData<MovieDetailsResponse>()
    val loading = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

    fun getMovieDetail(id: Int) {
        loading.postValue(true)
        viewModelScope.launch {
            val response = repository.getMovieDetail(id)
            if (response.isSuccessful) {
                detail.postValue(response.body())
            }
        }
        loading.postValue(false)
    }


    suspend fun exists(id: Int) = withContext(viewModelScope.coroutineContext) {
        repository.exists(id)
    }

    fun favoriteMovie(id: Int, entity: MovieEntity) {
        viewModelScope.launch {
            if (repository.exists(id)) {
                isFavorite.postValue(false)
                repository.delete(entity)
            } else {
                isFavorite.postValue(true)
                repository.insert(entity)
            }
        }
    }

}