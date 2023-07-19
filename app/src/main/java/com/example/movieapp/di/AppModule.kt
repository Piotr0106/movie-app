package com.example.movieapp.di

import com.example.movieapp.data.api.RetrofitClient
import com.example.movieapp.data.repo.MovieRepositoryImpl
import com.example.movieapp.data.repo.StorageRepositoryImpl
import com.example.movieapp.domain.repo.MovieRepository
import com.example.movieapp.domain.repo.StorageRepository
import com.example.movieapp.ui.favorite.FavoriteMoviesViewModel
import com.example.movieapp.ui.movie.MovieViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {

    single<MovieRepository> { MovieRepositoryImpl() }
    single<StorageRepository> { StorageRepositoryImpl(get()) }
    single { RetrofitClient() }

}

val viewModelsModule = module {
    viewModel { MovieViewModel() }
    viewModel { FavoriteMoviesViewModel() }
}