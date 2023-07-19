package com.example.movieapp.ui.favorite

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.room.Movie
import com.example.movieapp.domain.repo.StorageRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.movieapp.domain.Result
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FavoriteMoviesViewModel : ViewModel(), KoinComponent, LifecycleObserver {
    private val storageRepository: StorageRepository by inject()
    val favoriteMovies = MutableLiveData<List<Movie>>()
    private val disposable = CompositeDisposable()

    init {
        getFavoriteMoviesList()
    }

    fun getFavoriteMoviesList() {
        disposable.add(
            storageRepository.getFavoriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    when (it) {
                        is Result.OnSuccess -> {
                            handleSuccess(it.data)
                        }

                        is Result.OnError -> {
                            Log.i("error", "error")
                        }
                    }
                })
    }

    fun removeMovieFromFavorites(movie: Movie) {
        disposable.add(
            storageRepository.removeMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = {
                        Log.i("deleted movie", "${movie.title} has been removed")
                        getFavoriteMoviesList()
                    },
                    onError = { Log.e("inserted", it.message.orEmpty()) })
        )
    }

    private fun handleSuccess(data: List<Movie>) {
        favoriteMovies.value = data
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        getFavoriteMoviesList()
    }

}