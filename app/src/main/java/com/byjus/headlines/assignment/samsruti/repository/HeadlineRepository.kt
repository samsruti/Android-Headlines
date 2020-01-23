package com.byjus.headlines.assignment.samsruti.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.byjus.headlines.assignment.samsruti.database.HeadlinesAppDao
import com.byjus.headlines.assignment.samsruti.database.asDomainModel
import com.byjus.headlines.assignment.samsruti.domain.ApiResponse
import com.byjus.headlines.assignment.samsruti.domain.Headline
import com.byjus.headlines.assignment.samsruti.domain.asDatabaseModel
import com.byjus.headlines.assignment.samsruti.network.NewsApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class HeadlineRepository(
    private val apiService: NewsApiService,
    private val appDao: HeadlinesAppDao
) : BaseRepository() {



    val allHeadlineArticles: LiveData<List<Headline>> =
        Transformations.map(appDao.getHeadlines()) {
            it.asDomainModel()
        }


    suspend fun refreshArticles(){
        withContext(IO){
            val response = validApiCall(
                call = { apiService.getTopHeadlines("us") },
                errorMessage = "Error"
            )
            if (response != null) {
                addToCache(response)
            }

        }
    }

    private suspend fun addToCache(apiResponse: ApiResponse) {
        appDao.insertAllHeadlines(*apiResponse.asDatabaseModel())
    }

}