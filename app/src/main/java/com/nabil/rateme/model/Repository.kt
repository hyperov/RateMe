package com.nabil.rateme.model

import io.reactivex.Observable

interface Repository {

    fun insertAllMovies(vararg movie: Movie): List<Long>
    fun loadAllMovies(): Observable<List<Movie>>
    fun updateMovieRating(movieName: String, rating: Int): Int
}