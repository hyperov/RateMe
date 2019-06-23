package com.nabil.rateme.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nabil.rateme.TrampolineSchedulerProvider
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.MoviesRepository
import com.nabil.rateme.model.Repository
import io.reactivex.Observable
import org.junit.*
import org.mockito.Mockito.*


class MoviesViewModelTest {

    private lateinit var moviesRepository: Repository
    private lateinit var mainViewModel: MoviesViewModel
    private lateinit var progressObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<String>
    private lateinit var moviesObserver: Observer<List<Movie>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        moviesRepository = mock(MoviesRepository::class.java)
        mainViewModel = MoviesViewModel(moviesRepository,  TrampolineSchedulerProvider())
        progressObserver = mock(Observer<Boolean> {}.javaClass)
        errorObserver = mock(Observer<String> {}.javaClass)
        moviesObserver = mock(Observer<List<Movie>> {}.javaClass)

        mainViewModel.progressLiveData.observeForever(progressObserver)
        mainViewModel.errorLiveData.observeForever(errorObserver)
        mainViewModel.moviesLiveData.observeForever(moviesObserver)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_insertMovies_in_repository_is_invoked() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.insertAllMovies(movie))
            .thenReturn(listOf(1))

        mainViewModel.insertMovies(movie)
        verify(moviesRepository).insertAllMovies(movie)
    }

    @Test
    fun test_loadMovies_in_repository_is_invoked() {

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(emptyList()))

        mainViewModel.loadMovies()
        verify(moviesRepository).loadAllMovies()
    }

    @Test
    fun test_updateMovies_in_repository_is_invoked() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.updateMovieRating(movie.name, movie.rating))
            .thenReturn(2)

        mainViewModel.updateMovie(movie.name, movie.rating)
        verify(moviesRepository).updateMovieRating(movie.name, movie.rating)
    }

    @Test
    fun getMoviesLiveDataNotNull() {
        Assert.assertNotNull(mainViewModel.moviesLiveData)
    }

    @Test
    fun getProgressLiveDataNotNull() {
        Assert.assertNotNull(mainViewModel.progressLiveData)
    }

    @Test
    fun getErrorLiveDataNotNull() {
        Assert.assertNotNull(mainViewModel.errorLiveData)
    }

    @Test
    fun test_progressLiveData_value_from_loadMovies() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        mainViewModel.loadMovies()

        verify(progressObserver).onChanged(true)
        verify(progressObserver).onChanged(false)


    }

    @Test
    fun test_progressLiveData_value_from_insertMovies() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.insertAllMovies(movie))
            .thenReturn(listOf(1))

        mainViewModel.insertMovies(movie)
        verify(progressObserver).onChanged(true)
        verify(progressObserver).onChanged(false)
    }

    @Test
    fun test_progressLiveData_value_from_updateMovies() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.updateMovieRating(movie.name, movie.rating))
            .thenReturn(1)

        mainViewModel.updateMovie(movie.name, movie.rating)
        verify(progressObserver).onChanged(true)
        verify(progressObserver).onChanged(false)
    }

    @Test
    fun test_errorLiveData_value_loadMovies() {

        `when`(moviesRepository.loadAllMovies())
            .then { Observable.error<Any>(Exception("error")) }

        mainViewModel.loadMovies()

        verify(errorObserver).onChanged("error")

    }

    @Test
    fun test_errorLiveData_value_insertMovies() {
        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.insertAllMovies(movie))
            .then { null }

        mainViewModel.insertMovies(movie)
        verify(errorObserver).onChanged("Callable returned null")
    }

    @Test
    fun test_errorLiveData_value_updateMovies() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.updateMovieRating(movie.name, movie.rating))
            .then {Exception("error") }

        mainViewModel.updateMovie(movie.name, movie.rating)

        verify(errorObserver).onChanged("java.lang.Exception cannot be cast to java.lang.Integer")

    }

    @Test
    fun test_moviesLiveData_value() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        mainViewModel.loadMovies()
        verify(moviesObserver).onChanged(listOf(movie))

    }
}