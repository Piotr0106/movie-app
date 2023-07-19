package com.example.movieapp.data

typealias MoviesDomain = com.example.movieapp.domain.models.Movies
typealias ResultDomain = com.example.movieapp.domain.models.MovieItem
typealias PrimaryImageDomain = com.example.movieapp.domain.models.PrimaryImage
typealias TitleTextDomain = com.example.movieapp.domain.models.TitleText
typealias ReleaseYearDomain = com.example.movieapp.domain.models.ReleaseYear

typealias MoviesData = com.example.movieapp.data.models.Movies
typealias ResultData = com.example.movieapp.data.models.MovieItem
typealias PrimaryImageData = com.example.movieapp.data.models.PrimaryImage
typealias TitleTextData = com.example.movieapp.data.models.TitleText
typealias ReleaseYearData = com.example.movieapp.data.models.ReleaseYear

fun MoviesData.toDomain(): MoviesDomain = MoviesDomain(
    results = results.map { it.toDomain() }
)

fun ResultData.toDomain(): ResultDomain = ResultDomain(
    primaryImage = primaryImage.toDomain(),
    titleText = titleText.toDomain(),
    releaseYear = releaseYear.toDomain()
)

fun PrimaryImageData.toDomain(): PrimaryImageDomain = PrimaryImageDomain(
    id = id,
    width = width,
    height = height,
    url = url
)

fun TitleTextData.toDomain(): TitleTextDomain = TitleTextDomain(
    text = text
)

fun ReleaseYearData.toDomain(): ReleaseYearDomain = ReleaseYearDomain(
    year = year
)

