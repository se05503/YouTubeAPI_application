package com.example.ssg_tube.presentaion.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.network.RetroClient.youTubeRetrofit
import com.example.ssg_tube.presentaion.model.VideoModel
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _channelDetails = MutableLiveData<VideoModel>()
    val channelDetails: LiveData<VideoModel> = _channelDetails

    fun loadChannelData(videoModel: VideoModel) {
        viewModelScope.launch {
            val channelResponse = youTubeRetrofit.videoChannel(
                part = "snippet",
                id = videoModel.channelId,
                pageToken = ""
            )

            val channelDetails = channelResponse.items.firstOrNull()
                channelDetails?.let { channel ->
                    val channelUrl = channel.snippet.thumbnails.high.url
                    val channelName = channel.snippet.title
                    val updatedVideoModel = videoModel.copy(
                    channelIcon = channelUrl,
                    channelName = channelName
                )
                _channelDetails.value = updatedVideoModel
            }
        }
    }
}