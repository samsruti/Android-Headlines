package com.byjus.headlines.assignment.samsruti.ui.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byjus.headlines.assignment.samsruti.domain.News
import com.byjus.headlines.assignment.samsruti.viewmodel.BaseViewModel

class DetailsViewModel(news: News) : BaseViewModel() {
    private val _selectedHeadline = MutableLiveData<News>()

    val selectedHeadline: LiveData<News>
            get() = _selectedHeadline

    init {
        _selectedHeadline.value = news
    }

    class Factory(
        private val selectedHeadline: News) : ViewModelProvider.Factory {
            @Suppress("unchecked_cast")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                    return DetailsViewModel(selectedHeadline) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
    }
}
