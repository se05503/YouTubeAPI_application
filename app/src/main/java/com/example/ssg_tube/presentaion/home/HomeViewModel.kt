package com.example.ssg_tube.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.VideoRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: VideoRepository) : ViewModel() {
    private val _popularVideo: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val popularVideo: LiveData<List<VideoModel>> get() = _popularVideo

    private val _categoriesVideo = MutableLiveData<List<VideoModel>>()
    val categoriesVideo: LiveData<List<VideoModel>> get() = _categoriesVideo

    private val _channel: MutableLiveData<List<ChannelInfo>> = MutableLiveData()
    val channel: LiveData<List<ChannelInfo>> get() = _channel

    fun getPopularVideo() {
        viewModelScope.launch {
            val videos = repository.getPopularVideos()
            _popularVideo.postValue(videos)
        }
    }

    fun getCategoryVideo(categoryId: String) {
        viewModelScope.launch {
            val videos = repository.getCategoryVideos(categoryId)
            _categoriesVideo.postValue(videos)
            // 채널을 카테고리별로 맞는 채널을 추출하기위해 카테고리에서 추출한 channelId값을 getChannel에 넣어줌
            // 이로써 생성된 카테고리 비디오와 채널이 일치
            val channelId = videos.map { it.channelId }
            getChannel(channelId)
        }
    }

    private fun getChannel(channelId: List<String>) {
        viewModelScope.launch {
            val channels = repository.getChannel(channelId)
            _channel.postValue(channels)
        }
    }
}

class HomeViewModelFactory(private val repository: VideoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}