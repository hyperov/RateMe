package com.nabil.rateme.di

import android.content.Context
import androidx.room.Room
import com.nabil.rateme.model.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "database-movies"
        ).fallbackToDestructiveMigration().build()
    }
}