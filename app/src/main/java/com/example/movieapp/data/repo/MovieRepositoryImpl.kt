package com.example.movieapp.data.repo

import com.example.movieapp.data.MoviesData
import com.example.movieapp.domain.models.Movies
import com.example.movieapp.data.api.RetrofitClient
import com.example.movieapp.data.toDomain
import com.example.movieapp.domain.Result
import com.example.movieapp.domain.repo.MovieRepository
import com.example.movieapp.domain.toError
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieRepositoryImpl : MovieRepository, KoinComponent {
    private val retrofitClient: RetrofitClient by inject()
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
                .onErrorReturn { it.toResultError() }
        }
    }

    private fun <T> Throwable.toResultError(): Result<T> {
        val error = this.toError()
        return Result.withError(error)
    }

}