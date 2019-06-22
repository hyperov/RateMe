package com.nabil.rateme.viewmodel

import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.MoviesRepository
import com.nabil.rateme.model.Repository
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MoviesViewModelTest {

    private lateinit var moviesRepository: Repository
    private lateinit var mainViewModel: MoviesViewModel

    @Before
    fun setUp() {
        moviesRepository = Mockito.mock(MoviesRepository::class.java)
        mainViewModel = MoviesViewModel(moviesRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_insertMovies_in_repository_is_invoked() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        Mockito.`when`(moviesRepository.insertAllMovies(movie))
            .thenReturn(listOf(1))

        mainViewModel.insertMovies(movie)
        Mockito.verify(moviesRepository).insertAllMovies(movie)
    }

    @Test
    fun test_loadMovies_in_repository_is_invoked() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        Mockito.`when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(emptyList()))

        mainViewModel.loadMovies()
        Mockito.verify(moviesRepository).loadAllMovies()
    }

    @Test
    fun test_updateMovies_in_repository_is_invoked() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        Mockito.`when`(moviesRepository.updateMovieRating(movie.name, movie.rating))
            .thenReturn(2)

        mainViewModel.updateMovie(movie.name, movie.rating)
        Mockito.verify(moviesRepository).updateMovieRating(movie.name, movie.rating)
    }
}