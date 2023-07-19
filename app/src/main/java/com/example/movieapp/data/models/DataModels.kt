package com.example.movieapp.data.models

data class Movies(val results: List<MovieItem>)
data class MovieItem(
    val primaryImage: PrimaryImage,
    val titleText: TitleText,
    val releaseYear: ReleaseYear
)
data class PrimaryImage (
    val id: String,
    val width: Long,
    val height: Long,
    val url: String,
)

data class TitleText (
    val text: String
)

data class ReleaseYear(
    val year: Long
)

