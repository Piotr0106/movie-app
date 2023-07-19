package com.example.movieapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true) val cityId: Int = 0,
    val title: String,
    val releaseDate: Long
)
