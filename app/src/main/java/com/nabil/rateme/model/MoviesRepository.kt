package com.nabil.rateme.model

import io.reactivex.Observable

class MoviesRepository : Repository {

    override fun insertAllMovies(vararg movie: Movie): List<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAllMovies(): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateMovieRating(movieName: String, rating: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}