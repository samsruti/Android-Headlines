package com.byjus.headlines.assignment.samsruti.network

import android.os.Build
import com.byjus.headlines.assignment.samsruti.BuildConfig
import com.byjus.headlines.assignment.samsruti.domain.ApiResponse
import com.byjus.headlines.assignment.samsruti.domain.News
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsapi.org/"
private const val API_KEY = BuildConfig.NEWS_API_KEY


private val authenticatorApiService= Interceptor {chain->
    val interceptorURL = chain.request().url
        .newBuilder()
        .addQueryParameter("apiKey", API_KEY)
        .build()

    val interceptorRequest = chain.request()
        .newBuilder()
        .url(interceptorURL)
        .build()

    chain.proceed(interceptorRequest )
}


private val newsApiClient = OkHttpClient().newBuilder()
    .addInterceptor(authenticatorApiService)
    .build()


val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(newsApiClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface NewsApiService {
    //    Get all posts
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): Response<ApiResponse>
}

object NewsApi {
    val retrofitService : NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}
