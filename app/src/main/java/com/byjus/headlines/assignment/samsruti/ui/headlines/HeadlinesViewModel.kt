package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.byjus.headlines.assignment.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HeadlinesViewModel(private val repository: HeadlineRepository) : BaseViewModel() {


    val headlinesLiveData = repository.allHeadlineArticles

    private val _navigateToSelectedNews = MutableLiveData<News>()

    val navigateToSelectedNews: LiveData<News>
        get() = _navigateToSelectedNews

    private fun fetchArticleHeadlines() = viewModelScope.launch {
        repository.fetchHeadlines()
    }

    init {
        fetchArticleHeadlines()
    }

    fun displayNewsDetails(news: News) {
        _navigateToSelectedNews.value = news
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedNews.value = null
    }


}
