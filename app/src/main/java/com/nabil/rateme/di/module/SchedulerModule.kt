package com.nabil.rateme.di.module

import com.nabil.rateme.BaseSchedulerProvider
import com.nabil.rateme.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Singleton
    @Provides
    fun getScheduler(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}