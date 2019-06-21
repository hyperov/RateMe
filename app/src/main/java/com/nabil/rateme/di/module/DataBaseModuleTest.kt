package com.nabil.rateme.di.module

import android.content.Context
import androidx.room.Room
import com.nabil.rateme.model.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * dagger module to provide ROOM INSTANCE for testing
 */
@Module
class DataBaseModuleTest {

    @Singleton
    @Provides
    fun getDatabase(context: Context): MovieDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
    }

}