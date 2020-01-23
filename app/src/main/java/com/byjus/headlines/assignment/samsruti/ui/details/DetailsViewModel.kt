package com.byjus.headlines.assignment.samsruti.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byjus.headlines.assignment.samsruti.domain.Headline
import com.byjus.headlines.assignment.samsruti.viewmodel.BaseViewModel

class DetailsViewModel(headline: Headline) : BaseViewModel() {
    private val _selectedHeadline = MutableLiveData<Headline>()

    val selectedHeadline: LiveData<Headline>
            get() = _selectedHeadline

    init {
        _selectedHeadline.value = headline
    }

    class Factory(
        private val selectedHeadline: Headline) : ViewModelProvider.Factory {
            @Suppress("unchecked_cast")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                    return DetailsViewModel(selectedHeadline) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
    }
}
