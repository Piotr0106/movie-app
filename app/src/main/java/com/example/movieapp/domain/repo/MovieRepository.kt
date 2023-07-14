package com.example.movieapp.domain.repo

import com.example.movieapp.domain.Result
import com.example.movieapp.domain.models.Movies
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getMovies(list: String): Single<Result<Movies>>
}