package com.kishordahiwadkar.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kishordahiwadkar.movies.NETWORK_PAGE_SIZE
import com.kishordahiwadkar.movies.models.Movie
import com.kishordahiwadkar.movies.network.MoviesService
import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val service: MoviesService) {

    fun getSearchResultStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(service, query) }
        ).flow
    }

}