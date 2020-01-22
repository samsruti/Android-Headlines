package com.byjus.headlines.assignment.samsruti.repository

import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.network.NetworkResult
import com.byjus.headlines.assignment.samsruti.network.NewsApi
import com.byjus.headlines.assignment.samsruti.network.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeadlineRepository (private val apiService: NewsApiService): BaseRepository(){

    suspend fun getAllHeadlines(): MutableList<News>?{

        val res = validApiCall(
            call = {apiService.getTopHeadlines("us")},
            errorMessage = "Error"
        )
        return res?.articles?.toMutableList()
    }
}