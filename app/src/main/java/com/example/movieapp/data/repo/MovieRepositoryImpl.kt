package com.example.movieapp.data.repo

import com.example.movieapp.data.MoviesData
import com.example.movieapp.domain.models.Movies
import com.example.movieapp.data.RetrofitClient
import com.example.movieapp.data.toDomain
import com.example.movieapp.domain.Result
import com.example.movieapp.domain.repo.MovieRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer

class MovieRepositoryImpl : MovieRepository {
    private val retrofitClient = RetrofitClient()
    private val api = retrofitClient.api

    override fun getMovies(list: String): Single<Result<Movies>> {
        return api.getMovies(
            list = list
        ).compose(mapMoviesResponse())
    }

    private fun mapMoviesResponse(): SingleTransformer<MoviesData, Result<Movies>> {
        return SingleTransformer { upstream ->
            upstream
                .map { Result.withValue(it.toDomain()) }
        }
    }

}