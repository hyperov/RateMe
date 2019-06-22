package com.nabil.rateme.viewmodel

import androidx.lifecycle.ViewModel
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.Repository
import io.reactivex.Observable
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val moviesRepository: Repository) : ViewModel() {

    fun insertMovies(vararg movie: Movie) {

    }

    fun loadMovies(): Observable<List<Movie>> {
        return Observable.fromIterable(emptyList())
    }

    fun updateMovie(name: String, rating: Int) {

    }
}