package com.example.imdbtopmovies.models.register

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = ""
)
