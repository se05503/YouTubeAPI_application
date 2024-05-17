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

    fun getPopularVideo() {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoPopularList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR"
            )
            response.let {
                val popularVideos = it.items.map { item ->
                    VideoModel(
                        thumbnail = item.snippet.thumbnails.default.url,
                        title = item.snippet.title ,
                        date = "",
                        channelIcon = "",
                        channelName = "",
                        description = "",
                        channelId = "",
                        id = ""
                    )
                }
                _popularVideo.postValue(popularVideos)
            }
        }
    }

    fun getCategoryVideo(categoryId: String) {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoCategoriesList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR",
                videoCategoryId = categoryId
            )
            response.let {
                val categoryVideos = it.items.map { item ->
                    VideoModel(
                        thumbnail = item.snippet.thumbnails.default.url,
                        title = item.snippet.title,
                        date = "",
                        channelIcon = "",
                        channelName = "",
                        description = "",
                        channelId = "",
                        id = ""
                    )
                }
                _categoriesVideo.postValue(categoryVideos)
            }
        }
    }

    fun getChannel(channelIds: String) {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoChannel(
                part = "snippet",
                id = channelIds
            )
            response.let {
                val channels = it.items.map { item ->
                    item.snippet.thumbnails.default.url.let { url ->
                        VideoModel(
                            thumbnail = url,
                            title = "",
                            date = "",
                            channelIcon = url,
                            channelName = "",
                            description = "",
                            channelId = channelIds,
                            id = ""
                        )
                    }
                }
                _channel.postValue(channels)
            }
        }
    }

}