package com.nabil.rateme.model

import com.nabil.rateme.model.room.MovieDAO
import com.nabil.rateme.model.room.MovieDatabase
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRepository @Inject constructor(val database: MovieDatabase) : Repository {

    private var movieDAO: MovieDAO = database.getMoviesDao()

    override fun insertAllMovies(vararg movie: Movie): List<Long> {
        return movieDAO.insertAllMovies(*movie)
    }

    override fun loadAllMovies(): Observable<List<Movie>> {
        return movieDAO.loadAllMovies()
    }

    override fun updateMovieRating(movieName: String, rating: Float): Int {
        return movieDAO.updateMovieRating(movieName, rating)
    }
}