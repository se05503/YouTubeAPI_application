package com.example.ssg_tube.presentaion.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.model.VideoModel
import kotlinx.coroutines.launch

class SearchViewModel(private val apiService: YouTubeAPI) : ViewModel() {

    // Livedata
    private val _searchResults = MutableLiveData<List<VideoModel>>()
    val searchResults: LiveData<List<VideoModel>> get() = _searchResults

    var resItems: ArrayList<VideoModel> = ArrayList()

    fun videoResults(query: String) { // videoSearch 를 불러오기 위해선 suspend 를 사용해야 한다.
        resItems.clear()
        viewModelScope.launch { // 코루틴을 사용하여 비동기적으로 실행
            // videModelScope는 fragment 가 파괴 될 때 중단되어 메모리 누수가 방지됨
            val requestResponse = apiService.videoSearch( // 비동기적으로 실행되기 때
                part = "snippet",
                query = query,
                maxResults = 5, // 해결 완료
                order = "relevance",
                type = "video",
                videoType = "any"
            )

            val items = requestResponse.items
            for (item in items) {
                val thumbnail = item.snippet.thumbnails["default"]!!.url
                val title = item.snippet.title
                val id = item.id.videoId
                val channelId = item.snippet.channelId
                resItems.add(
                    VideoModel(
                        id = id, // channel에서 요구하는 id값이랑 다름 (video id임)
                        title = title,
                        thumbnail = thumbnail,
                        channelIcon = "",
                        channelName = "",
                        date = "",
                        description = "",
                        channelId = channelId
                    )
                )
            }
            Log.d("check", "$resItems")
            searchResult()
        }

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