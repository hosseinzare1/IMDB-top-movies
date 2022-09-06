package com.example.imdbtopmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imdbtopmovies.utils.Constants

@Entity(tableName = Constants.FAVORITES_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0, // 1
    var country: String = "", // USA
    var imdbRating: String = "", // 9.3
    var poster: String = "", // https://moviesapi.ir/images/tt0111161_poster.jpg
    var title: String = "", // The Shawshank Redemption
    var year: String = "" // 1994

)
