package com.example.imdbtopmovies.repository

import com.example.imdbtopmovies.db.FavoritesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FavoritesDao) {
    fun getFavoriteList() = dao.getAllFavorites()
}