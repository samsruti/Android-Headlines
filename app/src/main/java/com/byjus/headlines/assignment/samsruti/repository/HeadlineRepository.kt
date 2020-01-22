package com.byjus.headlines.assignment.samsruti.repository

import androidx.lifecycle.LiveData
import com.byjus.headlines.assignment.samsruti.database.DatabaseArticles
import com.byjus.headlines.assignment.samsruti.database.HeadlinesAppDao
import com.byjus.headlines.assignment.samsruti.network.NewsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HeadlineRepository(
    private val apiService: NewsApiService,
    private val appDao: HeadlinesAppDao
) : BaseRepository() {

    val allHeadlineArticles: LiveData<List<DatabaseArticles>> = appDao.getHeadlines()

//    suspend fun getAllHeadlinesFromNetwork(): MutableList<News>?{
//
//        val res = validApiCall(
//            call = {apiService.getTopHeadlines("us")},
//            errorMessage = "Error"
//        )
//        return res?.articles?.toMutableList()
//    }

    fun fetchHeadlines() {
        CoroutineScope(IO).launch {
            val response = validApiCall(
                call = { apiService.getTopHeadlines("us") },
                errorMessage = "Error"
            )
            val listHeadlines = response?.articles
            if (listHeadlines != null) {
                addToCache(listHeadlines)
            }
        }
    }

    private fun addToCache(headlines: List<DatabaseArticles>) {
        CoroutineScope(IO).launch {
            for (eachArticle in headlines) {
                appDao.insert(eachArticle)
            }
        }
    }
}