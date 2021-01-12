package com.kishordahiwadkar.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kishordahiwadkar.movies.repository.MoviesRepository
import com.kishordahiwadkar.movies.ui.home.HomeViewModel

class ViewModelFactory (private val repository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}