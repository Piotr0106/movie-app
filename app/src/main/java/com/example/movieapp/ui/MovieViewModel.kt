package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.repo.MovieRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.movieapp.domain.Result
import com.example.movieapp.domain.models.Movies
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepositoryImpl()
    val moviesData = MutableLiveData<Movies>()
    private val disposable = CompositeDisposable()
    val movieImage = MutableLiveData<String>()

    init {
        getMovie()
    }

    fun getMovie() {
        disposable.add(
            movieRepository.getMovies(list = "most_pop_movies")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { result ->
                    when (result) {
                        is Result.OnSuccess -> {
                            handleSuccess(result.data)
                        }
                        is Result.OnError -> {
                            Log.i("error","error")
                        }
                    }

                })
    }

    private fun handleSuccess(data: Movies) {
        moviesData.value = data
        movieImage.value = data.results[0].primaryImage.url
    }


}