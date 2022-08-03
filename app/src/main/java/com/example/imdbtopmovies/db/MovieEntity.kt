package com.example.imdbtopmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imdbtopmovies.utils.Constants

@Entity(tableName = Constants.FAVORITES_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey()
    val id: Int = 0, // 1
    val country: String = "", // USA
    val imdbRating: String = "", // 9.3
    val poster: String = "", // https://moviesapi.ir/images/tt0111161_poster.jpg
    val title: String = "", // The Shawshank Redemption
    val year: String = "" // 1994

)
