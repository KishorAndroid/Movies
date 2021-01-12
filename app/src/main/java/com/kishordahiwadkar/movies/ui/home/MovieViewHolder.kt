package com.kishordahiwadkar.movies.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kishordahiwadkar.movies.R
import com.kishordahiwadkar.movies.models.Movie

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.imagePoster)
    private val fav: ImageView = view.findViewById(R.id.imageFav)
    private var movieItem: Movie? = null

    init {
        fav.setOnClickListener {
            Toast.makeText(it.context, "Mark it as favourite", Toast.LENGTH_SHORT).show()
        }
        poster.setOnClickListener {
            view.context.startActivity(
                Intent(view.context, MovieDetailActivity::class.java).apply {
                    movieItem?.let {
                        putExtra("MOVIE", movieItem)
                    }
                }
            )
        }
    }

    fun bind(movie: Movie?) {
        movie?.let {
            movieItem = movie
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(poster);
        }
    }


    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_view_item, parent, false)
            return MovieViewHolder(view)
        }
    }
}