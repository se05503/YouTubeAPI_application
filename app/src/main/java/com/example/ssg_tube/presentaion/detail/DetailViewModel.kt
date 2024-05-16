package com.example.ssg_tube.presentaion.detail

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssg_tube.presentaion.model.VideoModel

class DetailViewModel : ViewModel() {
    private val _likedItems = MutableLiveData<List<VideoModel>>()
    val bookmarkedItems: LiveData<List<VideoModel>> get() = _likedItems




}