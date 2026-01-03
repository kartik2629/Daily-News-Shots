package com.example.dailynewsshots.data.remote

import com.example.dailynewsshots.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
    ): NewsResponse

}
