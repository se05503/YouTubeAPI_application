package com.example.ssg_tube.presentaion.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.data.model.SearchResponse
import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.model.SearchItemModel
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class SearchViewModel(private val apiService: YouTubeAPI) : ViewModel() {

    // Livedata
    private val _searchResults = MutableLiveData<List<SearchItemModel>>()
    val searchResults: LiveData<List<SearchItemModel>> get() = _searchResults

    var resItems: ArrayList<SearchItemModel> = ArrayList()

    fun videoResults(query: String) { // videoSearch 를 불러오기 위해선 suspend 를 사용해야 한다.
        resItems.clear()
        viewModelScope.launch {
            // videModelScope는 fragment 가 파괴 될 때 중단되어 메모리 누수가 방지됨
            val requestResponse = apiService.videoSearch(
                part = "snippet",
                query = query,
                maxResults = 5,
                order = "relevance",
                type = "video",
                videoType = "any"
            )
        }

        val items = requestResponse.items
        for (item in items) {
            val thumbnail = item.snippet.thumbnails["default"]!!.url
            val title = item.snippet.title
            resItems.add(SearchItemModel(title = title, url = thumbnail))
        }
        searchResult()
    }

    // 검색 결과를 LiveData에 설정
    private fun searchResult() {
        _searchResults.value = resItems
    }
}

class SearchViewModelFactory(private val apiService: YouTubeAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(apiService) as T
    }
}