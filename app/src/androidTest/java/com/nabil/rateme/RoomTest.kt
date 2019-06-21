package com.nabil.rateme

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nabil.rateme.model.room.MovieDAO
import com.nabil.rateme.model.room.MovieDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class RoomTest {

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


}
