package com.nabil.rateme.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nabil.rateme.TrampolineSchedulerProvider
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.MoviesRepository
import com.nabil.rateme.model.Repository
import io.reactivex.Observable
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.internal.verification.Times

class MoviesViewModelTest {

    private lateinit var moviesRepository: Repository
    private lateinit var mainViewModel: MoviesViewModel
    private lateinit var progressObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<String>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        moviesRepository = mock(MoviesRepository::class.java)
        mainViewModel = MoviesViewModel(moviesRepository)
        mainViewModel.schedulerProvider = TrampolineSchedulerProvider()
        progressObserver = mock(Observer<Boolean> {}.javaClass)
        errorObserver = mock(Observer<String> {}.javaClass)

        mainViewModel.progressLiveData.observeForever(progressObserver)
        mainViewModel.errorLiveData.observeForever(errorObserver)
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
    fun test_progressLiveData_value() {
        mainViewModel.progressLiveData.postValue(true)

        assert(mainViewModel.progressLiveData.value == true)

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        mainViewModel.loadMovies()

        verify(progressObserver).onChanged(true)
//        verify(progressObserver, times(2)).onChanged(false)
    }

    @Test
    fun test_errorLiveData_value() {
        mainViewModel.errorLiveData.postValue("error_message")

        assert(mainViewModel.errorLiveData.value == "error_message")


    }

    @Test
    fun test_moviesLiveData_value_from_loadMovies() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        mainViewModel.loadMovies()
        assert(mainViewModel.moviesLiveData.value == listOf(movie))
    }

    @Test
    fun test_moviesLiveData_value_from_() {

        val movie = Movie(name = "Avengers", image = 4, rating = 9)

        `when`(moviesRepository.loadAllMovies())
            .thenReturn(Observable.just(listOf(movie)))

        mainViewModel.loadMovies()
        assert(mainViewModel.moviesLiveData.value == listOf(movie))
    }
}