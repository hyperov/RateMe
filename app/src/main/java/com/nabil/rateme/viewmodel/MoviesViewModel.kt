package com.nabil.rateme.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.Repository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val moviesRepository: Repository) : ViewModel() {

    val progressLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    val moviesLiveData = MutableLiveData<List<Movie>>()

    fun insertMovies(vararg movie: Movie) {
        moviesRepository.insertAllMovies(*movie)
    }

    fun loadMovies() {

    }

    fun updateMovie(name: String, rating: Int) {

    }
}