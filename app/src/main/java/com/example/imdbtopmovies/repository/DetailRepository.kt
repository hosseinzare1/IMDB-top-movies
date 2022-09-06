package com.example.imdbtopmovies.repository

import com.example.imdbtopmovies.api.ApiService
import com.example.imdbtopmovies.db.FavoritesDao
import com.example.imdbtopmovies.db.MovieEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: FavoritesDao
) {
    suspend fun getMovieDetail(id: Int) = apiService.getMovieDetails(id)


    //Favorite
    suspend fun insert(entity: MovieEntity) = dao.insertMovie(entity)
    suspend fun delete(entity: MovieEntity) = dao.deleteMovie(entity)
    suspend fun exists(id: Int) = dao.isMovieFavorite(id)
}