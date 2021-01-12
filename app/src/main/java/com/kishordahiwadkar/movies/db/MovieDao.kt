package com.kishordahiwadkar.movies.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kishordahiwadkar.movies.models.Movie

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("select * from movie")
    fun getCountries(): LiveData<List<Movie>>
}