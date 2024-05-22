package com.example.ssg_tube.presentaion.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.YoutubeRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: YoutubeRepository) : ViewModel() {

    // Livedata
    private val _searchResults = MutableLiveData<List<VideoModel>>()
    val searchResults: LiveData<List<VideoModel>> get() = _searchResults

    private var nextPageToken: String? = null
    var currentOrder = ""

    fun getSearch(query: String, order: String) {
        viewModelScope.launch {
            val search = repository.getSearch(query, order, "")
            search
            _searchResults.postValue(search)
        }
    }

    fun loadNextPage(query: String) {
        nextPageToken?.let {token ->
            viewModelScope.launch {
                repository.getSearch(query, )
            }
        }
    }
}

class SearchViewModelFactory(private val repository: YoutubeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}