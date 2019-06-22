package com.nabil.rateme

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nabil.rateme.model.Movie
import com.nabil.rateme.model.room.MovieDAO
import com.nabil.rateme.model.room.MovieDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class RoomTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: MovieDatabase
    private lateinit var movieDAO: MovieDAO

    @Before
    @Throws(Exception::class)
    fun setUp() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDAO = database.getAdsDao()

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_database_is_created() {
        assert(database.isOpen)
    }

    @Test
    fun test_insert_and_select_movies() {

        val movie = Movie(name = "spider man", image = 4, rating = 3)
        movieDAO.insertAllMovies(movie)
        val observer = movieDAO.loadAllMovies().test()
        observer.assertNoErrors()
        observer.assertValueCount(1)
        observer.assertValue { movies: List<Movie> -> movies[0].name.equals(movie.name, true) }
    }

    @Test
    fun test_update_movie_by_name() {

        val movie = Movie(name = "spider man", image = 4, rating = 3)

        movieDAO.insertAllMovies(movie)
        movieDAO.loadAllMovies().test().assertValueCount(1)

        val update = movieDAO.updateMovieRating("spider man", 8)
        assert(update == 1)

        movieDAO.loadAllMovies().test().assertValue { list: List<Movie> ->
            list[0].name.contentEquals(movie.name) && list[0].rating == 8
        }
    }
}
