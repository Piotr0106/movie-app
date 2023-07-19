package com.example.movieapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: Movie): Completable

    @Delete
    fun delete(movie: Movie): Completable

    @Query("SELECT * FROM favorite_movies_table")
    fun getAllMovies(): Single<List<Movie>>
}