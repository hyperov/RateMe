package com.nabil.rateme.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.rateme.BaseSchedulerProvider
import com.nabil.rateme.livedata.SingleLiveEvent
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    val moviesRepository: Repository,
    val schedulerProvider: BaseSchedulerProvider
) : ViewModel() {

    val progressLiveData = SingleLiveEvent<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val moviesLiveData = MutableLiveData<List<Movie>>()

    private val disposable = CompositeDisposable()

    fun insertMovies(vararg movie: Movie) {
        val subscribe = Observable
            .fromCallable { moviesRepository.insertAllMovies(*movie) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { progressLiveData.value = true }
            .doFinally { progressLiveData.value = false }
            .subscribe({ }, { t: Throwable? -> errorLiveData.value = t!!.message })
        disposable.add(subscribe)
    }

    fun loadMovies() {
        val subscribe = moviesRepository.loadAllMovies()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { progressLiveData.value = true }
            .doFinally { progressLiveData.value = false }
            .subscribe({ movies: List<Movie> -> moviesLiveData.value = movies },
                { t: Throwable? -> errorLiveData.value = t!!.message })
        disposable.add(subscribe)
    }

    fun updateMovie(name: String, rating: Float) {
        val subscribe = Observable
            .fromCallable { moviesRepository.updateMovieRating(name, rating) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({}, { t: Throwable? -> errorLiveData.value = t!!.message })
        disposable.add(subscribe)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}