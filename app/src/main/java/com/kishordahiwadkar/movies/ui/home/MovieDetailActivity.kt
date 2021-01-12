package com.kishordahiwadkar.movies.ui.home

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.kishordahiwadkar.movies.R
import com.kishordahiwadkar.movies.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        intent.extras?.let {
            it.getParcelable<Movie>("MOVIE")?.let {movie ->
                findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title =
                    movie.title
                findViewById<TextView>(R.id.textTitle).text =
                    movie.title
            }
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Mark it as favourite", Snackbar.LENGTH_LONG).show()
        }
    }
}