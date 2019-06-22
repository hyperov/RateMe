package com.nabil.rateme.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nabil.rateme.databinding.ItemMovieBinding
import com.nabil.rateme.model.Movie
import com.squareup.picasso.Picasso
import java.util.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var data: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recipeBinding = ItemMovieBinding.inflate(layoutInflater)

        return MovieViewHolder(recipeBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MovieViewHolder(var itemViewBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(item: Movie) = run { itemViewBinding.movie = item }
    }
}

@BindingAdapter("image_picasso")
fun setImageViewResource(imageView: ImageView, @DrawableRes resource: Int) {
    Picasso.get().load(resource).into(imageView)
}