package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.domain.Source
import kotlinx.coroutines.*

class HeadlinesViewModel : ViewModel() {

    private val viewModelJob = Job()
    /**
     * Coroutines in a Main Thread
     */
    protected val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Coroutines in a Pool of Thread
     */
    protected val ioScope = CoroutineScope(Dispatchers.Default + viewModelJob)


    private val _navigateToSelectedNews = MutableLiveData<News>()

    private val _allNews = MutableLiveData<List<News>>()
    val allNews: LiveData<List<News>>
        get() = _allNews

    val navigateToSelectedNews: LiveData<News>
        get() = _navigateToSelectedNews

    fun displayNewsDetails(news: News) {
        _navigateToSelectedNews.value = news
    }

//    fun displayNewsDetailsComplete() {
//        _navigateToSelectedNews.value = null
//    }

    init {
        val news1 = News(author = "Amy Thompson",
            content = "SpaceX's Crew Dragon capsule has safely returned to Port Canaveral after a successful test of its launch escape system. (Image credit: Jim Bridenstine/NASA/Twitter)\\r\\nCAPE CANAVERAL, Fla. — SpaceX's Crew Dragon capsule returned to port Sunday night (Jan. 19), … [+3510 chars]",
            description= "SpaceX's Crew Dragon capsule has safely returned to Port Canaveral after a successful test of its launch escape system. (Image credit: Jim Bridenstine/NASA/Twitter)\\r\\nCAPE CANAVERAL, Fla. — SpaceX's Crew Dragon capsule returned to port Sunday night (Jan. 19)",
            publishedAt= "26-01-01",
            source= Source(id = 1,name = "Space.com"),
            title= "SpaceX's Crew Dragon returns to shore after successful abort test (photos) - Space.com",
            url= "https://www.space.com/spacex-crew-dragon-photos-after-abort-test.html",
            urlToImage= "https://cdn.mos.cms.futurecdn.net/ZHavnUd6iU8YbAcXq3Q2Cn-1200-80.jpeg"
        )

        val dummyNews = mutableListOf(news1,news1,news1,news1)
//        updateUI(dummyNews)
        mainScope.launch {
            _allNews.value = dummyNews
        }
    }

//    private fun updateUI(dummyNews: List<News>) {
//        mainScope.launch {
//            _allNews.value = dummyNews
//        }
//    }

    fun displayPropertyDetails(selectedNews: News) {
        _navigateToSelectedNews.value = selectedNews
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}
