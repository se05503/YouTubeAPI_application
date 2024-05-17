package com.example.ssg_tube.presentaion.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.data.model.CategoryItem
import com.example.ssg_tube.network.RetroClient
import com.example.ssg_tube.presentaion.model.CategoryModel
import com.example.ssg_tube.presentaion.model.VideoModel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _popularVideo: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val popularVideo: LiveData<List<VideoModel>> get() = _popularVideo

    private val _channel: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val channel: LiveData<List<VideoModel>> get() = _channel

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> get() = _categories

    fun popularVideoResponse() {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoPopularList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR"
            )
            // videos API
            val videoModel = response.items.map {
                VideoModel(
                    thumbnail = it.snippet.thumbnails.default.url ?: "",
                    title = it.snippet.title ?: "",
                    date = "",
                    channelIcon = "",
                    channelName = "",
                    description = ""
                )
            }
            _popularVideo.postValue(videoModel)
        }
    }

    fun fetchVideosByCategory(categoryId: String) {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoCategoriesList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR",
                videoCategoryId = categoryId
            )
            if (response.items.isNotEmpty()) {
                val videos = response.items.map {
                    VideoModel(
                        thumbnail = it.snippet.thumbnails.default.url ?: "",
                        title = it.snippet.title ?: "",
                        date = "",
                        channelIcon = "",
                        channelName = "",
                        description = ""
                    )
                }
                _popularVideo.postValue(videos)
            }
        }
    }

}