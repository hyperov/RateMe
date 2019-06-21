package com.nabil.rateme.application

import android.app.Application
import com.nabil.rateme.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

//    fun getAppComponent() = applicationInjector()
}