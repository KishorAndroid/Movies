package com.kishordahiwadkar.movies.repository

import androidx.paging.PagingSource
import com.kishordahiwadkar.movies.NETWORK_PAGE_SIZE
import com.kishordahiwadkar.movies.STARTING_PAGE_INDEX
import com.kishordahiwadkar.movies.models.Movie
import com.kishordahiwadkar.movies.network.MoviesService
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val service: MoviesService,
    private val query: String
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.popularMovies(position)
            val repos = response.results
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}