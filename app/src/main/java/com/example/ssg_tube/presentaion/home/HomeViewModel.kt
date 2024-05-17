package com.example.ssg_tube.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.network.RetroClient
import com.example.ssg_tube.presentaion.model.VideoModel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _popularVideo: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val popularVideo: LiveData<List<VideoModel>> get() = _popularVideo

    private val _categoriesVideo = MutableLiveData<List<VideoModel>>()
    val categoriesVideo: LiveData<List<VideoModel>> get() = _categoriesVideo

    private val _channel: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val channel: LiveData<List<VideoModel>> get() = _channel

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
                    description = "",
                    channelId = "",
                    id = ""
                )
            }
            _popularVideo.postValue(videoModel)
        }
    }

    fun categoryVideo(categoryId: String) {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoCategoriesList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR",
                videoCategoryId = categoryId
            )
                // videos API
                val videos = response.items.map {
                    VideoModel(
                        thumbnail = it.snippet.thumbnails.default.url ?: "",
                        title = it.snippet.title ?: "",
                        date = "",
                        channelIcon = "",
                        channelName = "",
                        description = "",
                        channelId = "",
                        id = ""
                    )
                }
                _categoriesVideo.postValue(videos)
        }
    }

}