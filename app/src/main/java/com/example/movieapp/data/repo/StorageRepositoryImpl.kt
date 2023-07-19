package com.example.movieapp.data.repo

import android.content.Context
import com.example.movieapp.data.room.Movie
import com.example.movieapp.data.room.MovieDao
import com.example.movieapp.data.room.MovieDatabase
import com.example.movieapp.domain.repo.StorageRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import com.example.movieapp.domain.Result
import com.example.movieapp.domain.toError

class StorageRepositoryImpl(context: Context) : StorageRepository {
    private var movieDao: MovieDao

    init {
        val database = MovieDatabase
            .getInstance(context.applicationContext)
        movieDao = database.movieDao()
    }

    override fun addMovie(title: String, releaseDate: Long): Completable {
        return movieDao.insert(Movie(title = title, releaseDate = releaseDate))
    }

    override fun getFavoriteMovies(): Single<Result<List<Movie>>> {
        return movieDao.getAllMovies()
            .compose(mapMovies())
    }

    override fun removeMovie(movie: Movie): Completable {
        return movieDao.delete(movie)
    }

    private fun mapMovies():
            SingleTransformer<List<Movie>, Result<List<Movie>>> {
        return SingleTransformer { upstream ->
            upstream
                .map { Result.withValue(it) }
                .onErrorReturn { it.toResultError() }
        }
    }
    private fun <T> Throwable.toResultError(): Result<T> {
        val error = this.toError()
        return Result.withError(error)
    }
}