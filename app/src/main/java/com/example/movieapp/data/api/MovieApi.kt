package com.example.movieapp.data.api

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.models.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers("X-RapidAPI-Key: ${BuildConfig.API_KEY}")
    @GET("titles/random")
    fun getMovies(
        @Query("list") list: String
    ): Single<Movies>

}