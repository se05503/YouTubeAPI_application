package com.example.ssg_tube.presentaion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssg_tube.presentaion.model.DetailModel

class HomeViewModel : ViewModel() {
    private val _event: MutableLiveData<List<DetailModel>> = MutableLiveData()
    val event: LiveData<List<DetailModel>> get() = _event

}