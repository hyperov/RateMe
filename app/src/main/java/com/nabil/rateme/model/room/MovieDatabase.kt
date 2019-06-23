package com.nabil.rateme.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nabil.rateme.model.Movie

/**
 * Room Database class include intites,version number
 * and schema settings
 */
@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MovieDAO
}