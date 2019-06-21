package com.nabil.rateme.di.component

import android.content.Context
import com.nabil.rateme.application.MyApp
import com.nabil.rateme.di.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
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