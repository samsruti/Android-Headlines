package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.domain.Source
import com.byjus.headlines.assignment.samsruti.network.NewsApi
import com.byjus.headlines.assignment.samsruti.repository.HeadlineRepository
import com.byjus.headlines.assignment.samsruti.viewmodel.BaseViewModel
import kotlinx.coroutines.*

class HeadlinesViewModel : BaseViewModel() {




    private val _navigateToSelectedNews = MutableLiveData<News>()

    private val _allNews = MutableLiveData<List<News>>()
    val allNews: LiveData<List<News>>
        get() = _allNews

    val navigateToSelectedNews: LiveData<News>
        get() = _navigateToSelectedNews

    private val repository : HeadlineRepository = HeadlineRepository(NewsApi.retrofitService)


    val headlinesLiveData = MutableLiveData<MutableList<News>>()

    private fun fetchHeadlines(){
        mainScope.launch {
            val allHeadlines = repository.getAllHeadlines()
            headlinesLiveData.postValue(allHeadlines)
        }
    }

    init {
        val news1 = News(author = "Amy Thompson",
            content = "SpaceX's Crew Dragon capsule has safely returned to Port Canaveral after a successful test of its launch escape system. (Image credit: Jim Bridenstine/NASA/Twitter)\\r\\nCAPE CANAVERAL, Fla. — SpaceX's Crew Dragon capsule returned to port Sunday night (Jan. 19), … [+3510 chars]",
            description= "SpaceX's Crew Dragon capsule has safely returned to Port Canaveral after a successful test of its launch escape system. (Image credit: Jim Bridenstine/NASA/Twitter)\\r\\nCAPE CANAVERAL, Fla. — SpaceX's Crew Dragon capsule returned to port Sunday night (Jan. 19)",
            publishedAt= "26-01-01",
            source= Source(id = "space",name = "Space.com"),
            title= "SpaceX's Crew Dragon returns to shore after successful abort test (photos) - Space.com",
            url= "https://www.space.com/spacex-crew-dragon-photos-after-abort-test.html",
            urlToImage= "https://cdn.mos.cms.futurecdn.net/ZHavnUd6iU8YbAcXq3Q2Cn-1200-80.jpeg"
        )

        val dummyNews = mutableListOf(news1,news1,news1,news1)
        updateUI(dummyNews)

        fetchHeadlines()
    }

    private fun updateUI(dummyNews: List<News>) {
        mainScope.launch {
            _allNews.value = dummyNews
        }
    }

    fun displayNewsDetails(news: News) {
        _navigateToSelectedNews.value = news
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedNews.value = null
    }


}
