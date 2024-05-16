package com.example.ssg_tube.presentaion.ui.detail

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssg_tube.presentaion.model.DetailModel
import com.google.gson.GsonBuilder

class DetailViewModel : ViewModel() {
    private val _likedItems = MutableLiveData<List<DetailModel>>()
    val bookmarkedItems: LiveData<List<DetailModel>> get() = _likedItems




}