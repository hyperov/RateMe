package com.nabil.rateme.di.component

import android.content.Context
import com.nabil.rateme.application.MyApp
import com.nabil.rateme.di.module.DatabaseModule
import com.nabil.rateme.di.module.RepositoryModule
import com.nabil.rateme.di.module.SchedulerModule
import com.nabil.rateme.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        RepositoryModule::class,
        SchedulerModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    override fun inject(instance: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}