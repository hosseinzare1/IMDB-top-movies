package com.example.imdbtopmovies.db

import androidx.room.*
import com.example.imdbtopmovies.utils.Constants

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM ${Constants.FAVORITES_TABLE_NAME}")
    fun getAllFavorites(): MutableList<MovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.FAVORITES_TABLE_NAME} WHERE id =:movieID) ")
    fun isMovieFavorite(movieID: Int): MutableList<MovieEntity>

}