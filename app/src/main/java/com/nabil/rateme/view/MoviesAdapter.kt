package com.nabil.rateme.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nabil.rateme.R
import com.nabil.rateme.databinding.ItemMovieBinding
import com.nabil.rateme.model.Movie
import com.squareup.picasso.Picasso
import java.util.*

class MoviesAdapter(private var data: List<Movie> = ArrayList()) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recipeBinding = DataBindingUtil.inflate<ItemMovieBinding>(
            layoutInflater,
            R.layout.item_movie, parent, false
        )

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