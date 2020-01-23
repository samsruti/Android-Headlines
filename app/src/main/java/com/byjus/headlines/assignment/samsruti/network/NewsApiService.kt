package com.byjus.headlines.assignment.samsruti.network

import com.byjus.headlines.assignment.samsruti.domain.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    // API For Top Headlines posts/articles
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): Response<ApiResponse>
}
