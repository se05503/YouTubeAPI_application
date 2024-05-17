package com.example.ssg_tube.presentaion.detail

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.data.model.ChannelItem
import com.example.ssg_tube.network.RetroClient.youTubeRetrofit
import com.example.ssg_tube.presentaion.model.VideoModel
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrl

class DetailViewModel : ViewModel() {
//    private val _likedItems = MutableLiveData<List<VideoModel>>()
//    val bookmarkedItems: LiveData<List<VideoModel>> get() = _likedItems

    private val _channelDetails = MutableLiveData<List<VideoModel>>()
    val channelDetails: LiveData<List<VideoModel>> = _channelDetails


    fun loadChannelData(videoModel: VideoModel) {
        viewModelScope.launch {
            val response = youTubeRetrofit.videoChannel(
                part = "snippet",
                id = videoModel.channelId
            )
            val channelDetails = response.items.firstOrNull()
            channelDetails?.let { channel ->
                val channelUrl = channel.snippet.thumbnails.high.url
                val channelName = channel.snippet.title
                val description = channel.snippet.description
                val updatedVideoModel = videoModel.copy(
                    channelIcon = channelUrl,
                    channelName = channelName,
                    description = description
                )
                _channelDetails.value = listOf(updatedVideoModel)
            }

        }

    }
}