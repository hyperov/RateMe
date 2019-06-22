package com.nabil.rateme.di.module

import com.nabil.rateme.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * dagger module to provide injection to main Activity
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): MainActivity
}