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

    fun popularVideoMapper() {
        viewModelScope.launch {
            val response = RetroClient.youTubeRetrofit.videoPopularList(
                part = "snippet",
                chart = "mostPopular",
                regionCode = "KR"
            )
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

}