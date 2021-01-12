package com.kishordahiwadkar.movies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kishordahiwadkar.movies.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): CountryDao

    companion object {

        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase? {
            if (INSTANCE == null) {
                synchronized(MoviesDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesDatabase::class.java, "movie_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}