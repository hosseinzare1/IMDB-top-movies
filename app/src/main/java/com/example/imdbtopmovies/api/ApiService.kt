package com.example.imdbtopmovies.api

import com.example.imdbtopmovies.models.home.GenresResponse
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import com.example.imdbtopmovies.models.register.RegisterBody
import com.example.imdbtopmovies.models.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("register")
    suspend fun registerUser(@Body userBody: RegisterBody): Response<RegisterResponse>


    @GET("genres/{id}/movies")
    suspend fun getTopMovies(@Path("id") id :Int):Response<TopMoviesResponse>

    @GET("movies")
    suspend fun getLastMovies():Response<TopMoviesResponse>

    @GET("genres")
    suspend fun getGenres():Response<GenresResponse>
}