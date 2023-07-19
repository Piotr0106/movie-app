package com.example.movieapp.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.room.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.movieapp.domain.Result
import com.example.movieapp.domain.models.Movies
import io.reactivex.rxjava3.disposables.CompositeDisposable
import com.example.movieapp.domain.Error
import com.example.movieapp.domain.repo.MovieRepository
import com.example.movieapp.domain.repo.StorageRepository
import com.example.movieapp.ui.core.UiEvents
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieViewModel : ViewModel(), KoinComponent {
    private val movieRepository: MovieRepository by inject()
    private val storageRepository: StorageRepository by inject()
    private val uiEvents = UiEvents<Event>()
    val events: Observable<Event> = uiEvents.stream()
    private val disposable = CompositeDisposable()

    val moviesData = MutableLiveData<Movies>()
    val isFavorite = MutableLiveData(false)
    val movieImage =
        MutableLiveData("https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Question_mark_%28black%29.svg/800px-Question_mark_%28black%29.svg.png")
    val status = MutableLiveData(Status.Loading)
    val movieTitle = MutableLiveData<String>()
    val movieYear = MutableLiveData<Long>()

    enum class Status { Loading, Success, Error }

    init {
        getMovie()
    }

    fun getMovie() {
        status.postValue(Status.Loading)
        disposable.add(
            movieRepository.getMovies(list = "most_pop_movies")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { result ->
                    status.postValue(Status.Success)
                    when (result) {
                        is Result.OnSuccess -> {
                            handleSuccess(result.data)
                        }

                        is Result.OnError -> {
                            handleError(result.error)
                        }
                    }

                })
    }

    fun addMovieToFavorites() {
        Event.OnAddToFavoritesClick.let { uiEvents.post(it) }
        val title = moviesData.value?.results?.get(0)?.titleText?.text
        val year = moviesData.value?.results?.get(0)?.releaseYear?.year
        if (!isFavorite.value!!) {//if not added to favorites
            isFavorite.value = !isFavorite.value!!
            disposable.add(
                storageRepository.addMovie(title = title.orEmpty(), releaseDate = year!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {
                            Log.i("inserted", "$title inserted!")

                        },
                        onError = { Log.e("inserted", it.message.orEmpty()) })
            )
        }

    }

    private fun handleSuccess(data: Movies) {
        isFavorite.value = false
        moviesData.value = data
        movieTitle.value = data.results[0].titleText.text
        movieImage.value = data.results[0].primaryImage.url
        movieYear.value = data.results[0].releaseYear.year
    }

    private fun handleError(error: Error) {
        Log.e(this::class.java.simpleName, error.message)
    }

    sealed class Event {
        object OnAddToFavoritesClick : Event()
    }
}