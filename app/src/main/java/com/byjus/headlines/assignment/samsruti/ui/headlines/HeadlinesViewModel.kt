package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byjus.headlines.assignment.samsruti.domain.ApiStatus
import com.byjus.headlines.assignment.samsruti.domain.Headline
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.byjus.headlines.assignment.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class HeadlinesViewModel(private val repository: HeadlineRepository) : BaseViewModel() {


    val headlinesLiveData = repository.allHeadlineArticles

    private val _navigateToSelectedNews = MutableLiveData<Headline>()

    val navigateToSelectedNetworkNews: LiveData<Headline>
        get() = _navigateToSelectedNews


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    init {
        refresh()
    }

    fun refresh(){
        mainScope.launch {
            try {
                repository.refreshArticles()
            }catch (e: Exception){
                Timber.e("Error: $e")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun displayNewsDetails(headline: Headline) {
        _navigateToSelectedNews.value = headline
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedNews.value = null
    }


}
