package com.example.imdbtopmovies.di

import android.content.Context
import androidx.room.Room
import com.example.imdbtopmovies.db.FavoriteDB
import com.example.imdbtopmovies.db.FavoritesDao
import com.example.imdbtopmovies.db.MovieEntity
import com.example.imdbtopmovies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoriteDB = Room.databaseBuilder(
        context, FavoriteDB::class.java, Constants.FAVORITES_DATABASE_NAME
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideEntity(): MovieEntity = MovieEntity()


    @Provides
    @Singleton
    fun provideFavoriteDao(db: FavoriteDB): FavoritesDao = db.favoriteDao()

}