package com.nabil.rateme.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nabil.rateme.model.Movie
import io.reactivex.Observable

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAllMovies(vararg movie: Movie): List<Long>

    @Query("SELECT * FROM movies")
    fun loadAllMovies(): Observable<List<Movie>>

    @Query("UPDATE movies SET rating = :rating WHERE name = :movieName")
    fun updateMovieRating(movieName: String, rating: Int): Int
}