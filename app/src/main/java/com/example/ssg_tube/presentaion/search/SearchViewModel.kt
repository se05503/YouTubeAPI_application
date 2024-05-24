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

    var currentOrder = ""
    var nextPageToken: String? = null

    fun getSearch(query: String, order: String) {
        viewModelScope.launch {
            val search = repository.getSearch(query, order, "")
            nextPageToken = search.nextToken
            _searchResults.value = search.items
        }
    }

    fun getNextPage(query: String) {
        viewModelScope.launch {
            nextPageToken?.let { token ->
                val nextSearch = repository.getSearch(query, currentOrder, token)
                nextPageToken = nextSearch.nextToken // nextPageToken 값은 계속 업데이트 합니다.
                val currentItem = _searchResults.value ?: emptyList()
                _searchResults.value = currentItem + nextSearch.items
            }
        }
    }
}

class SearchViewModelFactory(private val repository: YoutubeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}