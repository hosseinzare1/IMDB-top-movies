package com.example.imdbtopmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoritesDao
}