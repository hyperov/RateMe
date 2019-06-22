package com.nabil.rateme.di.module

import com.nabil.rateme.model.MoviesRepository
import com.nabil.rateme.model.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindRepository(repository: MoviesRepository): Repository
}