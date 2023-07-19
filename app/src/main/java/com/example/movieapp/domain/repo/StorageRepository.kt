package com.example.movieapp.domain.repo

import com.example.movieapp.data.room.Movie
import com.example.movieapp.domain.Result
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StorageRepository {
    fun addMovie(title: String, releaseDate: Long): Completable
    fun getFavoriteMovies(): Single<Result<List<Movie>>>
    fun removeMovie(movie: Movie) : Completable
}