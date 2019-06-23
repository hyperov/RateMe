package com.nabil.rateme

import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.MoviesRepository
import com.nabil.rateme.model.Repository
import com.nabil.rateme.model.room.MovieDAO
import com.nabil.rateme.model.room.MovieDatabase
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.internal.verification.NoMoreInteractions

class MoviesRepositoryTest {

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDAO: MovieDAO
    private lateinit var moviesRepository: Repository

    @Before
    fun setUp() {
        movieDatabase = mock(MovieDatabase::class.java)
        `when`(movieDatabase.getMoviesDao()).thenReturn(mock(MovieDAO::class.java))
        moviesRepository = MoviesRepository(movieDatabase)

        movieDAO = movieDatabase.getMoviesDao()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_insertAllMovies_in_database_true() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9F)

        `when`(movieDAO.insertAllMovies(movie))
            .thenReturn(listOf(1))

        moviesRepository.insertAllMovies(movie)
        verify(movieDAO).insertAllMovies(movie)
    }

    @Test
    fun test_loadAllMovies_in_database_true() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9F)

        `when`(movieDAO.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        moviesRepository.loadAllMovies()
        verify(movieDAO).loadAllMovies()
        verify(movieDAO, NoMoreInteractions()).loadAllMovies()
    }

    @Test
    fun test_updateMovieRating_in_database_true() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9F)

        `when`(movieDAO.updateMovieRating(movie.name, movie.rating))
            .thenReturn(1)

        moviesRepository.updateMovieRating(movie.name, movie.rating)
        verify(movieDAO).updateMovieRating(movie.name, movie.rating)
    }
}