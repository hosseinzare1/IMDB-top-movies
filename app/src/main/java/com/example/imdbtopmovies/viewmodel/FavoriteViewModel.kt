package com.example.imdbtopmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import com.example.imdbtopmovies.db.FavoriteDB
import com.example.imdbtopmovies.db.FavoritesDao
import com.example.imdbtopmovies.db.MovieEntity
import com.example.imdbtopmovies.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) :
    ViewModel() {

    val favoriteList = MutableLiveData<List<MovieEntity>>()
    val empty = MutableLiveData<Boolean>()

    fun getFavorites() {
        viewModelScope.launch {
            val favorites = repository.getFavoriteList()
            if (favorites.isNotEmpty()) {
                favoriteList.postValue(favorites)
                empty.postValue(false)
            } else {

                empty.postValue(true)
            }


        }

    }

}