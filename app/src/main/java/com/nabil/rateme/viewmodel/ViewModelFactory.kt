package com.nabil.rateme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nabil.rateme.BaseSchedulerProvider
import com.nabil.rateme.model.Repository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(val repository: Repository,val schedulerProvider: BaseSchedulerProvider) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(repository,schedulerProvider) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}