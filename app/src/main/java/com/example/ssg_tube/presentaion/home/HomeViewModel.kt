package com.example.ssg_tube.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssg_tube.presentaion.model.VideoModel

class HomeViewModel : ViewModel() {
    private val _event: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val event: LiveData<List<VideoModel>> get() = _event

}