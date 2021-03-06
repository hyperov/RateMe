package com.nabil.rateme.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.nabil.rateme.R
import com.nabil.rateme.databinding.ActivityMainBinding
import com.nabil.rateme.databinding.ContentMainBinding
import com.nabil.rateme.model.Movie
import com.nabil.rateme.viewmodel.MoviesViewModel
import com.nabil.rateme.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var moviesViewModel: MoviesViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var contentMainBinding: ContentMainBinding
    private lateinit var movies: Array<String>


    private var adapter: MoviesAdapter? = null
    var isRandomStop = true


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView()
        createViewModel()
        createViewModelObservers()
        viewMovies()

    }

    private fun setContentView() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        contentMainBinding = activityMainBinding.content
        setSupportActionBar(activityMainBinding.toolbar)
        contentMainBinding.rvMovies.itemAnimator = DefaultItemAnimator()
        activityMainBinding.customFab.setOnClickListener { updateRandomRating() }
    }

    @SuppressLint("ResourceType")
    private fun viewMovies() {
        movies = resources.getStringArray(R.array.movies)
        val images = resources.obtainTypedArray(R.array.images)

        val movie1 = Movie(name = movies[0], rating = 0F, image = images.getResourceId(0, 0))
        val movie2 = Movie(name = movies[1], rating = 0F, image = images.getResourceId(1, 0))
        val movie3 = Movie(name = movies[2], rating = 0F, image = images.getResourceId(2, 0))
        val movie4 = Movie(name = movies[3], rating = 0F, image = images.getResourceId(3, 0))
        val movie5 = Movie(name = movies[4], rating = 0F, image = images.getResourceId(4, 0))
        val movie6 = Movie(name = movies[5], rating = 0F, image = images.getResourceId(5, 0))
        val movie7 = Movie(name = movies[6], rating = 0F, image = images.getResourceId(6, 0))
        val movie8 = Movie(name = movies[7], rating = 0F, image = images.getResourceId(7, 0))
        val movie9 = Movie(name = movies[8], rating = 0F, image = images.getResourceId(8, 0))
        val movie10 = Movie(name = movies[9], rating = 0F, image = images.getResourceId(9, 0))

        moviesViewModel.insertMovies(
            movie1,
            movie2,
            movie3,
            movie4,
            movie5,
            movie6,
            movie7,
            movie8,
            movie9,
            movie10
        )
        images.recycle()

        moviesViewModel.loadMovies()
    }


    private fun createViewModelObservers() {

        moviesViewModel.errorLiveData.observe(this, Observer<String?> {
            activityMainBinding.root.showSnackBar(it!!)
            Log.e("error observer", it)
        })

        moviesViewModel.progressLiveData.observe(this, Observer {
            Log.d("progress livedata", it.toString())
            activityMainBinding.isProgress = it!!
        })

        moviesViewModel.moviesLiveData.observe(this, Observer {

            val sortedByDescendingList = it!!.sortedByDescending { movie -> movie.rating }
            if (adapter == null) {
                adapter = MoviesAdapter(sortedByDescendingList) { rating, name -> updateRating(rating, name) }
                contentMainBinding.rvMovies.adapter = adapter
            } else adapter!!.swapData(sortedByDescendingList)

        })

        moviesViewModel.updateLiveData.observe(this, Observer {
            adapter?.apply {
                notifyDataSetChanged()
            }
        })

    }

    private fun updateRating(rating: Float, name: String) {
        moviesViewModel.updateMovie(rating, name)
    }

    private fun updateRandomRating() {
        isRandomStop = !isRandomStop
        if (isRandomStop) moviesViewModel.randomDisposable?.dispose()
        else
            moviesViewModel.updateRandomMovie(movies, isRandomStop)
    }

    private fun createViewModel() {
        moviesViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun View.showSnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .show()
    }
}
