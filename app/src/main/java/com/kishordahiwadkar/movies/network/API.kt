package com.kishordahiwadkar.movies.network

import com.kishordahiwadkar.movies.BuildConfig
import com.kishordahiwadkar.movies.models.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("popular")
    suspend fun popularMovies(@Query("page") pageNumber: Int): MovieResponse
}

val clientBuilder: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        var request = chain.request()

        val url =
            request.url().newBuilder().addQueryParameter("api_key", BuildConfig.apikey).build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }.build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/movie/")
    .client(clientBuilder)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service: MoviesService = retrofit.create(MoviesService::class.java)