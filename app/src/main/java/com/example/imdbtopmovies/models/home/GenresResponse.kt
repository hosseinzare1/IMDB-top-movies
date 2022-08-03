package com.example.imdbtopmovies.models.home


import com.google.gson.annotations.SerializedName

class GenresResponse : ArrayList<GenresResponse.GenresResponseItem>(){
    data class GenresResponseItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}